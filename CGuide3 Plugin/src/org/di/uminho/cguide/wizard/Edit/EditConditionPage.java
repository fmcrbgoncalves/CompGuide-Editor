package org.di.uminho.cguide.wizard.Edit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;

public class EditConditionPage extends AbstractOWLWizardPanel {

	public static final String ID = "EditConditionPage";

	public static final String title = "Condition Editor";

	private JLabel clinicaltask_label;
	private DefaultListModel<String> model_total;

	private JTextArea info_text;

	private Map<String, String> info_list;

	private JLabel info_label;
	public JList list_total;

	public EditConditionPage(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Condition you wish to edit.");

		info_list = getDataConditions(getConditions());

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 130, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		clinicaltask_label = new JLabel("Condition List:");
		GridBagConstraints gbc_stopclinicaltask_label = new GridBagConstraints();
		gbc_stopclinicaltask_label.anchor = GridBagConstraints.NORTH;
		gbc_stopclinicaltask_label.insets = new Insets(0, 0, 5, 5);
		gbc_stopclinicaltask_label.gridx = 1;
		gbc_stopclinicaltask_label.gridy = 1;
		parent.add(clinicaltask_label, gbc_stopclinicaltask_label);

		model_total = new DefaultListModel<String>();
		for (OWLNamedIndividual a : getConditions()) {
			model_total.addElement(a.getIRI().getFragment());
		}

		info_label = new JLabel("Selected Condition Info:");
		GridBagConstraints gbc_info_label1 = new GridBagConstraints();
		gbc_info_label1.insets = new Insets(0, 0, 5, 5);
		gbc_info_label1.gridx = 2;
		gbc_info_label1.gridy = 1;
		parent.add(info_label, gbc_info_label1);

		list_total = new JList();
		list_total.setModel(model_total);
		list_total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		GridBagConstraints gbc_stopclinicaltask_list = new GridBagConstraints();
		gbc_stopclinicaltask_list.insets = new Insets(0, 0, 5, 5);
		gbc_stopclinicaltask_list.fill = GridBagConstraints.BOTH;
		gbc_stopclinicaltask_list.gridx = 1;
		gbc_stopclinicaltask_list.gridy = 2;
		parent.add(new JScrollPane(list_total), gbc_stopclinicaltask_list);

		info_text = new JTextArea();
		info_text.setRows(5);
		info_text.setColumns(100);
		info_text.setEnabled(false);
		GridBagConstraints gbc_info_text = new GridBagConstraints();
		gbc_info_text.fill = GridBagConstraints.BOTH;
		gbc_info_text.insets = new Insets(0, 0, 5, 5);
		gbc_info_text.gridx = 2;
		gbc_info_text.gridy = 2;
		parent.add(new JScrollPane(info_text), gbc_info_text);

		// Add Listener
		list_total.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					info_text.setText(info_list.get(list_total.getSelectedValue()));
					info_text.setCaretPosition(0);
				}
			}
		});
	}

	public Object getBackPanelDescriptor() {
		return SelectClassPage.ID;
	}

	public Object getNextPanelDescriptor() {
		if (list_total.isSelectionEmpty())
			return EditConditionPage.ID;
		else
			return WizardPanel.FINISH;
	}

	public Set<OWLNamedIndividual> getConditions() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#Condition"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	// Return Map<Key,String>
	// Key -> NomeID do Individual Condition
	// String -> String com informacoes da Condition relacionada

	public Map<String, String> getDataConditions(Set<OWLNamedIndividual> list) {
		Map<String, String> conditions = new HashMap<String, String>();

		for (OWLNamedIndividual individual : list) {

			int type = -1;
			String ID = new String();
			String numericalValue = new String(); // Decimal
			String qualitativeValue = new String(); // String
			String parameterIdentifier = new String(); // String
			String conditionParameter = new String(); // String
			String unit = new String(); // String
			String comparisonOperator = new String(); // Choose individual
			String info = new String();

			// Get Data Properties of each Condition
			Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = individual
					.getDataPropertyValues(getOWLModelManager().getActiveOntology());
			for (Map.Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data.entrySet()) {

				try {
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("numericalValue")) {
						numericalValue = entry.getValue().iterator().next().getLiteral();
						type = 0;
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("qualitativeValue")) {
						qualitativeValue = entry.getValue().iterator().next().getLiteral();
						type = 1;
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("parameterIdentifier")) {
						parameterIdentifier = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("conditionParameter")) {
						conditionParameter = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("unit")) {
						unit = entry.getValue().iterator().next().getLiteral();
					}
				} catch (Exception e) {
				}
			}

			// Get Object Property of ComparisonOperator
			OWLObjectPropertyExpression objectexpression = getOWLModelManager().getOWLDataFactory()
					.getOWLObjectProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#hasComparisonOperator"));
			;

			try {
				OWLIndividual comparison_individual = individual
						.getObjectPropertyValues(objectexpression, getOWLModelManager().getActiveOntology()).iterator()
						.next();
				comparisonOperator = comparison_individual.asOWLNamedIndividual().getIRI().getFragment();
			} catch (Exception e) {
			}

			if (type == 0)
				info = "ConditionID:" + individual.getIRI().getFragment() + "\nNumerical Value:" + numericalValue
						+ "\nParameterIdentifier:" + parameterIdentifier + "\nConditionParameter:" + conditionParameter
						+ "\nUnit:" + unit + "\nComparisonOperator:" + comparisonOperator;
			else if (type == 1)
				info = "ConditionID:" + individual.getIRI().getFragment() + "\nQualitative Value:" + qualitativeValue
						+ "\nParameterIdentifier:" + parameterIdentifier + "\nConditionParameter:" + conditionParameter
						+ "\nUnit:" + unit + "\nComparisonOperator:" + comparisonOperator;

			conditions.put(individual.getIRI().getFragment(), info);

		}

		return conditions;
	}

}
