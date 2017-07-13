package org.di.uminho.cguide.wizard.Create.Condition;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.di.uminho.cguide.wizard.Create.SelectClassPage;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

public class CreateConditionPage1 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateConditionPage1";

	public static final String title = "Insert Condition Data Required";

	public int condition_type = 0;
	private JRadioButton numericalvalue_ratiobutton;
	private JRadioButton qualitativevalue_ratiobutton;
	public JTextField numericalvalue_textfield;
	public JTextField qualitativevalue_textfield;
	private JLabel comparisonoperator_label;
	public JComboBox comparisonoperator_comboBox;
	private JLabel parameteridentifier_label;
	public JTextField parameteridentifier_textfield;
	private JLabel conditionparameter_label;
	public JTextField conditionparameter_textfield;
	private JLabel unit_label;
	public JTextField unit_textfield;

	public CreateConditionPage1(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the creation of a Clinical Condition.");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 106, 5, 180, 0 };
		gridBagLayout.rowHeights = new int[] { 23, 23, 20, 20, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		numericalvalue_ratiobutton = new JRadioButton("Numerical Value:", true);
		GridBagConstraints gbc_numericalvalue_ratiobutton = new GridBagConstraints();
		gbc_numericalvalue_ratiobutton.anchor = GridBagConstraints.NORTH;
		gbc_numericalvalue_ratiobutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_numericalvalue_ratiobutton.insets = new Insets(0, 0, 5, 5);
		gbc_numericalvalue_ratiobutton.gridwidth = 2;
		gbc_numericalvalue_ratiobutton.gridx = 0;
		gbc_numericalvalue_ratiobutton.gridy = 0;
		parent.add(numericalvalue_ratiobutton, gbc_numericalvalue_ratiobutton);

		numericalvalue_textfield = new JTextField();
		numericalvalue_textfield.setColumns(10);
		numericalvalue_textfield.setEnabled(true);
		GridBagConstraints gbc_numericalvalue_textfield = new GridBagConstraints();
		gbc_numericalvalue_textfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_numericalvalue_textfield.insets = new Insets(0, 0, 5, 0);
		gbc_numericalvalue_textfield.gridx = 2;
		gbc_numericalvalue_textfield.gridy = 0;
		parent.add(numericalvalue_textfield, gbc_numericalvalue_textfield);

		qualitativevalue_ratiobutton = new JRadioButton("Qualitative Value:");
		GridBagConstraints gbc_qualitativevalue_ratiobutton = new GridBagConstraints();
		gbc_qualitativevalue_ratiobutton.anchor = GridBagConstraints.NORTHWEST;
		gbc_qualitativevalue_ratiobutton.insets = new Insets(0, 0, 5, 5);
		gbc_qualitativevalue_ratiobutton.gridwidth = 2;
		gbc_qualitativevalue_ratiobutton.gridx = 0;
		gbc_qualitativevalue_ratiobutton.gridy = 1;
		parent.add(qualitativevalue_ratiobutton, gbc_qualitativevalue_ratiobutton);

		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(numericalvalue_ratiobutton);
		buttongroup.add(qualitativevalue_ratiobutton);

		// Add Listener
		numericalvalue_ratiobutton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// NumericalValue Condition -> type 0
					condition_type = 0;
					numericalvalue_textfield.setEnabled(true);
					qualitativevalue_textfield.setEnabled(false);
					qualitativevalue_textfield.setText("");
				}
			}
		});

		qualitativevalue_ratiobutton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// QualitativeValue Condition -> type 1
					condition_type = 1;
					numericalvalue_textfield.setEnabled(false);
					qualitativevalue_textfield.setEnabled(true);
					numericalvalue_textfield.setText("");
				}
			}
		});

		qualitativevalue_textfield = new JTextField();
		qualitativevalue_textfield.setColumns(10);
		qualitativevalue_textfield.setEnabled(false);
		GridBagConstraints gbc_qualitativevalue_textfield = new GridBagConstraints();
		gbc_qualitativevalue_textfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_qualitativevalue_textfield.insets = new Insets(0, 0, 5, 0);
		gbc_qualitativevalue_textfield.gridx = 2;
		gbc_qualitativevalue_textfield.gridy = 1;
		parent.add(qualitativevalue_textfield, gbc_qualitativevalue_textfield);

		comparisonoperator_label = new JLabel("Comparison Operator");
		GridBagConstraints gbc_comparisonoperator_label = new GridBagConstraints();
		gbc_comparisonoperator_label.anchor = GridBagConstraints.EAST;
		gbc_comparisonoperator_label.insets = new Insets(0, 0, 5, 5);
		gbc_comparisonoperator_label.gridx = 0;
		gbc_comparisonoperator_label.gridy = 2;
		parent.add(comparisonoperator_label, gbc_comparisonoperator_label);

		comparisonoperator_comboBox = new JComboBox();
		comparisonoperator_comboBox.addItem("-");
		for (OWLNamedIndividual individual : getComparisonOperator()) {
			comparisonoperator_comboBox.addItem(individual.getIRI().getFragment());
		}
		comparisonoperator_comboBox.setSelectedIndex(0);
		GridBagConstraints gbc_comparisonoperator_comboBox = new GridBagConstraints();
		gbc_comparisonoperator_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comparisonoperator_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comparisonoperator_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comparisonoperator_comboBox.gridwidth = 2;
		gbc_comparisonoperator_comboBox.gridx = 1;
		gbc_comparisonoperator_comboBox.gridy = 2;
		parent.add(comparisonoperator_comboBox, gbc_comparisonoperator_comboBox);

		parameteridentifier_label = new JLabel("Parameter Identifier:");
		GridBagConstraints gbc_parameteridentifier_label = new GridBagConstraints();
		gbc_parameteridentifier_label.anchor = GridBagConstraints.WEST;
		gbc_parameteridentifier_label.insets = new Insets(0, 0, 5, 5);
		gbc_parameteridentifier_label.gridx = 0;
		gbc_parameteridentifier_label.gridy = 3;
		parent.add(parameteridentifier_label, gbc_parameteridentifier_label);

		parameteridentifier_textfield = new JTextField();
		parameteridentifier_textfield.setColumns(10);
		GridBagConstraints gbc_parameteridentifier_textfield = new GridBagConstraints();
		gbc_parameteridentifier_textfield.anchor = GridBagConstraints.NORTH;
		gbc_parameteridentifier_textfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_parameteridentifier_textfield.insets = new Insets(0, 0, 5, 0);
		gbc_parameteridentifier_textfield.gridwidth = 2;
		gbc_parameteridentifier_textfield.gridx = 1;
		gbc_parameteridentifier_textfield.gridy = 3;
		parent.add(parameteridentifier_textfield, gbc_parameteridentifier_textfield);

		conditionparameter_label = new JLabel("Condition Parameter:");
		GridBagConstraints gbc_conditionparameter_label = new GridBagConstraints();
		gbc_conditionparameter_label.anchor = GridBagConstraints.WEST;
		gbc_conditionparameter_label.insets = new Insets(0, 0, 5, 5);
		gbc_conditionparameter_label.gridx = 0;
		gbc_conditionparameter_label.gridy = 4;
		parent.add(conditionparameter_label, gbc_conditionparameter_label);

		conditionparameter_textfield = new JTextField();
		conditionparameter_textfield.setColumns(10);
		GridBagConstraints gbc_conditionparameter_textfield = new GridBagConstraints();
		gbc_conditionparameter_textfield.anchor = GridBagConstraints.NORTH;
		gbc_conditionparameter_textfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_conditionparameter_textfield.insets = new Insets(0, 0, 5, 0);
		gbc_conditionparameter_textfield.gridwidth = 2;
		gbc_conditionparameter_textfield.gridx = 1;
		gbc_conditionparameter_textfield.gridy = 4;
		parent.add(conditionparameter_textfield, gbc_conditionparameter_textfield);

		unit_label = new JLabel("Unit:");
		GridBagConstraints gbc_unit_label = new GridBagConstraints();
		gbc_unit_label.anchor = GridBagConstraints.NORTHEAST;
		gbc_unit_label.insets = new Insets(0, 0, 0, 5);
		gbc_unit_label.gridx = 0;
		gbc_unit_label.gridy = 5;
		parent.add(unit_label, gbc_unit_label);

		unit_textfield = new JTextField();
		unit_textfield.setColumns(10);
		GridBagConstraints gbc_unit_textfield = new GridBagConstraints();
		gbc_unit_textfield.anchor = GridBagConstraints.NORTH;
		gbc_unit_textfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_unit_textfield.gridwidth = 2;
		gbc_unit_textfield.gridx = 1;
		gbc_unit_textfield.gridy = 5;
		parent.add(unit_textfield, gbc_unit_textfield);

	}

	public Object getBackPanelDescriptor() {
		return SelectClassPage.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreateConditionPage2.ID;
	}

	public Set<OWLNamedIndividual> getComparisonOperator() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#ComparisonOperator"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}
}
