package org.di.uminho.cguide.wizard.Edit.Formula;

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

public class EditFormulaPage3 extends AbstractOWLWizardPanel {

	public static final String ID = "EditFormulaPage3";

	public static final String title = "Formula Result Parameter";

	private JLabel resultparameter_label;
	private DefaultListModel<String> model_total;

	private JTextArea info_text;

	private Map<String, String> info_list;

	private JLabel info_label;
	public JList list_total;

	String hasresultParameter = new String();

	public EditFormulaPage3(OWLEditorKit owlEditorKit, String formula_individual) {
		super(ID, title, owlEditorKit);
		getFormula(formula_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the next Result Parameters of the Formula.");

		info_list = getDataParameters(getParameters());

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 130, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		resultparameter_label = new JLabel("Parameter List:");
		GridBagConstraints gbc_resultparameter_label = new GridBagConstraints();
		gbc_resultparameter_label.anchor = GridBagConstraints.NORTH;
		gbc_resultparameter_label.insets = new Insets(0, 0, 5, 5);
		gbc_resultparameter_label.gridx = 1;
		gbc_resultparameter_label.gridy = 1;
		parent.add(resultparameter_label, gbc_resultparameter_label);

		model_total = new DefaultListModel<String>();
		model_total.addElement("-");
		for (OWLNamedIndividual a : getParameters()) {
			model_total.addElement(a.getIRI().getFragment());
		}

		info_label = new JLabel("Selected Parameter Info:");
		GridBagConstraints gbc_info_label1 = new GridBagConstraints();
		gbc_info_label1.insets = new Insets(0, 0, 5, 5);
		gbc_info_label1.gridx = 2;
		gbc_info_label1.gridy = 1;
		parent.add(info_label, gbc_info_label1);

		list_total = new JList();
		list_total.setModel(model_total);
		list_total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_total.setSelectedIndex(0);
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
		return EditFormulaPage2.ID;
	}

	public Object getNextPanelDescriptor() {
		return EditFormulaPage4.ID;
	}

	@Override
	public void aboutToDisplayPanel() {
		if (!(this.hasresultParameter.isEmpty())) {
			list_total.setSelectedValue(this.hasresultParameter, true);
		}
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Set<OWLNamedIndividual> getParameters() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#Parameter"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Map<String, String> getDataParameters(Set<OWLNamedIndividual> list) {
		Map<String, String> conditions = new HashMap<String, String>();

		for (OWLNamedIndividual individual : list) {
			try {

				String possibleValue = new String(); // Decimal
				String variableName = new String(); // String
				String parameterIdentifier = new String(); // String
				String questionParameter = new String(); // String
				String unit = new String(); // String
				String parameterDescription = new String(); // Choose individual
				String info = new String();

				// Get Data Properties of each Parameter
				Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = individual
						.getDataPropertyValues(getOWLModelManager().getActiveOntology());
				for (Map.Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data.entrySet()) {
					try {

						if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("possibleValue")) {
							possibleValue = entry.getValue().iterator().next().getLiteral();
						}
						if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("variableName")) {
							variableName = entry.getValue().iterator().next().getLiteral();
						}
						if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("parameterIdentifier")) {
							parameterIdentifier = entry.getValue().iterator().next().getLiteral();
						}
						if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("questionParameter")) {
							questionParameter = entry.getValue().iterator().next().getLiteral();
						}
						if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("unit")) {
							unit = entry.getValue().iterator().next().getLiteral();
						}
						if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("parameterDescription")) {
							parameterDescription = entry.getValue().iterator().next().getLiteral();
						}
					} catch (Exception e) {
					}
				}

				info = "ParameterID:" + individual.getIRI().getFragment() + "\nPossible Value:" + possibleValue
						+ "\nVariable Name:" + variableName + "\nParameterIdentifier:" + parameterIdentifier
						+ "\nQuestion Parameter:" + questionParameter + "\nUnit:" + unit + "\nParameter Description:"
						+ parameterDescription;

				conditions.put(individual.getIRI().getFragment(), info);

			} catch (Exception e) {
			}
		}

		return conditions;
	}

	public void getFormula(String formula_individual_name) {
		OWLNamedIndividual formula_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#"
						+ formula_individual_name));

		try {
			OWLObjectPropertyExpression hasresultparameter_objectproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLObjectProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#hasResultParameter"));
			OWLNamedIndividual hasResultParameter_individual = formula_individual
					.getObjectPropertyValues(hasresultparameter_objectproperty,
							getOWLModelManager().getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			this.hasresultParameter = hasResultParameter_individual.getIRI().getFragment();
		} catch (Exception e) {
		}
	}

}
