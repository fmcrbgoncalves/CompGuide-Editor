package org.di.uminho.cguide.wizard.Create.Action;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

public class CreateActionPage6 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateActionPage6";

	public static final String title = "Action Pre-Conditions";

	private JLabel condition_label;
	private JLabel added_label;

	private JList list_added;
	public DefaultListModel<String> model_added;

	private JList list_total;
	private DefaultListModel<String> model_total;

	private JTextArea info_text;
	private JLabel info_label;

	private JButton btnAdd;
	private JButton btnRemove;

	private Map<String, String> info_list;

	private int last_selected_index_total;
	private int last_selected_index_added;

	public CreateActionPage6(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Pre-Conditions of the Action.");

		info_list = getDataConditions(getConditions());

		last_selected_index_total = -1;
		last_selected_index_added = -1;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 95, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		condition_label = new JLabel("Condition List:");
		GridBagConstraints gbc_lblSpecialities = new GridBagConstraints();
		gbc_lblSpecialities.anchor = GridBagConstraints.NORTH;
		gbc_lblSpecialities.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecialities.gridx = 1;
		gbc_lblSpecialities.gridy = 1;
		parent.add(condition_label, gbc_lblSpecialities);

		added_label = new JLabel("Added:");
		GridBagConstraints gbc_lblAdd = new GridBagConstraints();
		gbc_lblAdd.anchor = GridBagConstraints.NORTH;
		gbc_lblAdd.insets = new Insets(0, 0, 5, 0);
		gbc_lblAdd.gridx = 3;
		gbc_lblAdd.gridy = 1;
		parent.add(added_label, gbc_lblAdd);

		model_total = new DefaultListModel<String>();
		for (OWLNamedIndividual a : getConditions()) {
			model_total.addElement(a.getIRI().getFragment());
		}
		list_total = new JList();
		list_total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// list_total.setCellRenderer(getOWLEditorKit().getWorkspace().createOWLCellRenderer());
		list_total.setModel(model_total);
		list_total.setFixedCellHeight(20);
		list_total.setFixedCellWidth(100);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridheight = 2;
		// gbc_list.gridwidth = 1;
		gbc_list.gridx = 1;
		gbc_list.gridy = 2;
		parent.add(new JScrollPane(list_total), gbc_list);

		btnAdd = new JButton(new AbstractAction("-> Add ->") {
			public void actionPerformed(ActionEvent e) {
				if (list_total.getSelectedIndex() > -1) {
					model_added.addElement(list_total.getSelectedValue().toString());
					model_total.remove(list_total.getSelectedIndex());
					list_added.setModel(model_added);
					list_total.setModel(model_total);
				}
			}
		});

		GridBagConstraints gbc_btnadd = new GridBagConstraints();
		gbc_btnadd.anchor = GridBagConstraints.SOUTH;
		gbc_btnadd.insets = new Insets(0, 0, 5, 5);
		gbc_btnadd.gridx = 2;
		gbc_btnadd.gridy = 2;
		parent.add(btnAdd, gbc_btnadd);

		model_added = new DefaultListModel<String>();
		list_added = new JList();
		list_added.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// list_added.setCellRenderer(getOWLEditorKit().getWorkspace().createOWLCellRenderer());
		list_added.setFixedCellHeight(20);
		list_added.setFixedCellWidth(100);
		list_added.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					info_text.setText(info_list.get(list_added.getSelectedValue()));
					info_text.setCaretPosition(0);
					list_total.clearSelection(); // <- Deselect item in list
				}
			}
		});

		// Add Listener
		list_total.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					info_text.setText(info_list.get(list_total.getSelectedValue()));
					info_text.setCaretPosition(0);
					list_added.clearSelection(); // <- Deselect item in list
				}
			}
		});

		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridheight = 2;
		// gbc_list_1.gridwidth = 1;
		gbc_list_1.gridx = 3;
		gbc_list_1.gridy = 2;
		parent.add(new JScrollPane(list_added), gbc_list_1);

		btnRemove = new JButton(new AbstractAction("<- Remove <-") {
			public void actionPerformed(ActionEvent e) {
				if (list_added.getSelectedIndex() > -1) {
					model_total.addElement(list_added.getSelectedValue().toString());
					model_added.remove(list_added.getSelectedIndex());
					list_total.setModel(model_total);
					list_added.setModel(model_added);
				}
			}
		});

		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 3;
		parent.add(btnRemove, gbc_button);

		info_label = new JLabel("Selected Condition Info:");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblInfo.gridx = 4;
		gbc_lblInfo.gridy = 1;
		parent.add(info_label, gbc_lblInfo);

		info_text = new JTextArea();
		info_text.setRows(5);
		info_text.setColumns(100);
		info_text.setEnabled(false);
		GridBagConstraints gbc_info_text = new GridBagConstraints();
		gbc_info_text.gridheight = 2;
		gbc_info_text.insets = new Insets(0, 0, 5, 0);
		gbc_info_text.fill = GridBagConstraints.BOTH;
		gbc_info_text.gridx = 4;
		gbc_info_text.gridy = 2;
		parent.add(new JScrollPane(info_text), gbc_info_text);

	}

	public Object getBackPanelDescriptor() {
		return CreateActionPage5.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreateActionPage7.ID;
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
