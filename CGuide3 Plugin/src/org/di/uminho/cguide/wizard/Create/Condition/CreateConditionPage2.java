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

import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

public class CreateConditionPage2 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateConditionPage2";

	public static final String title = "Insert Temporal Restriction Data Required";

	public int temporalrestriction_type = 0;
	private JRadioButton maxmintemporalrestriction_ratiobutton;
	private JRadioButton temporalrestriction_ratiobutton;
	public JTextField maxtemporalrestriction_textfield;
	public JTextField temporalrestriction_textfield;
	private JLabel temporaloperator_label;
	public JComboBox temporaloperator_combobox;
	private JLabel temporalunit_label;
	public JTextField mintemporalrestriction_textfield;
	public JComboBox temporalunit_combobox;

	public CreateConditionPage2(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert the Temporal Restriction for this Clinical Condition.");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 106, 5, 180, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 23, 0, 23, 20, 20, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		maxmintemporalrestriction_ratiobutton = new JRadioButton("Max/Min Temporal Restriction:", true);
		GridBagConstraints gbc_maxmintemporalrestriction_ratiobutton = new GridBagConstraints();
		gbc_maxmintemporalrestriction_ratiobutton.anchor = GridBagConstraints.EAST;
		gbc_maxmintemporalrestriction_ratiobutton.insets = new Insets(0, 0, 5, 5);
		gbc_maxmintemporalrestriction_ratiobutton.gridwidth = 2;
		gbc_maxmintemporalrestriction_ratiobutton.gridx = 0;
		gbc_maxmintemporalrestriction_ratiobutton.gridy = 0;
		parent.add(maxmintemporalrestriction_ratiobutton, gbc_maxmintemporalrestriction_ratiobutton);

		maxtemporalrestriction_textfield = new JTextField();
		maxtemporalrestriction_textfield.setColumns(10);
		maxtemporalrestriction_textfield.setEnabled(true);
		maxtemporalrestriction_textfield.setToolTipText("Enter Maximum Temporal Restriction");
		GridBagConstraints gbc_maxtemporalrestriction_textfield = new GridBagConstraints();
		gbc_maxtemporalrestriction_textfield.insets = new Insets(0, 0, 5, 5);
		gbc_maxtemporalrestriction_textfield.gridx = 2;
		gbc_maxtemporalrestriction_textfield.gridy = 0;
		parent.add(maxtemporalrestriction_textfield, gbc_maxtemporalrestriction_textfield);

		mintemporalrestriction_textfield = new JTextField();
		mintemporalrestriction_textfield.setEnabled(true);
		mintemporalrestriction_textfield.setColumns(10);
		mintemporalrestriction_textfield.setToolTipText("Enter Minimum Temporal Restriction");
		GridBagConstraints gbc_mintemporalrestriction_textfield = new GridBagConstraints();
		gbc_mintemporalrestriction_textfield.insets = new Insets(0, 0, 5, 5);
		gbc_mintemporalrestriction_textfield.gridx = 2;
		gbc_mintemporalrestriction_textfield.gridy = 1;
		parent.add(mintemporalrestriction_textfield, gbc_mintemporalrestriction_textfield);

		temporalrestriction_ratiobutton = new JRadioButton("Average Temporal Restriction:");
		GridBagConstraints gbc_temporalrestriction_ratiobutton = new GridBagConstraints();
		gbc_temporalrestriction_ratiobutton.anchor = GridBagConstraints.EAST;
		gbc_temporalrestriction_ratiobutton.insets = new Insets(0, 0, 5, 5);
		gbc_temporalrestriction_ratiobutton.gridwidth = 2;
		gbc_temporalrestriction_ratiobutton.gridx = 0;
		gbc_temporalrestriction_ratiobutton.gridy = 2;
		parent.add(temporalrestriction_ratiobutton, gbc_temporalrestriction_ratiobutton);

		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(maxmintemporalrestriction_ratiobutton);
		buttongroup.add(temporalrestriction_ratiobutton);

		// Add Listener
		maxmintemporalrestriction_ratiobutton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Temporal Restriction max/min -> type 0
					temporalrestriction_type = 0;
					maxtemporalrestriction_textfield.setEnabled(true);
					mintemporalrestriction_textfield.setEnabled(true);
					temporalrestriction_textfield.setEnabled(false);
					temporalrestriction_textfield.setText("");
				}
			}
		});

		temporalrestriction_ratiobutton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Temporal Restriction max/min -> type 1
					temporalrestriction_type = 1;
					maxtemporalrestriction_textfield.setEnabled(false);
					mintemporalrestriction_textfield.setEnabled(false);
					temporalrestriction_textfield.setEnabled(true);
					maxtemporalrestriction_textfield.setText("");
					mintemporalrestriction_textfield.setText("");
				}
			}
		});

		temporalrestriction_textfield = new JTextField();
		temporalrestriction_textfield.setColumns(10);
		temporalrestriction_textfield.setEnabled(false);
		GridBagConstraints gbc_temporalrestriction_textfield = new GridBagConstraints();
		gbc_temporalrestriction_textfield.insets = new Insets(0, 0, 5, 5);
		gbc_temporalrestriction_textfield.gridx = 2;
		gbc_temporalrestriction_textfield.gridy = 2;
		parent.add(temporalrestriction_textfield, gbc_temporalrestriction_textfield);

		temporaloperator_label = new JLabel("Temporal Operator:");
		GridBagConstraints gbc_temporaloperator_label = new GridBagConstraints();
		gbc_temporaloperator_label.anchor = GridBagConstraints.EAST;
		gbc_temporaloperator_label.gridwidth = 2;
		gbc_temporaloperator_label.insets = new Insets(0, 0, 5, 5);
		gbc_temporaloperator_label.gridx = 0;
		gbc_temporaloperator_label.gridy = 3;
		parent.add(temporaloperator_label, gbc_temporaloperator_label);

		temporaloperator_combobox = new JComboBox();
		temporaloperator_combobox.addItem("-");
		for (OWLNamedIndividual individual : getTemporalOperator()) {
			temporaloperator_combobox.addItem(individual.getIRI().getFragment());
		}
		temporaloperator_combobox.setSelectedIndex(0);
		GridBagConstraints gbc_temporaloperator_combobox = new GridBagConstraints();
		gbc_temporaloperator_combobox.anchor = GridBagConstraints.NORTH;
		gbc_temporaloperator_combobox.fill = GridBagConstraints.HORIZONTAL;
		gbc_temporaloperator_combobox.insets = new Insets(0, 0, 5, 5);
		gbc_temporaloperator_combobox.gridx = 2;
		gbc_temporaloperator_combobox.gridy = 3;
		parent.add(temporaloperator_combobox, gbc_temporaloperator_combobox);

		temporalunit_label = new JLabel("Temporal Unit:");
		GridBagConstraints gbc_temporalunit_label = new GridBagConstraints();
		gbc_temporalunit_label.anchor = GridBagConstraints.EAST;
		gbc_temporalunit_label.gridwidth = 2;
		gbc_temporalunit_label.insets = new Insets(0, 0, 5, 5);
		gbc_temporalunit_label.gridx = 0;
		gbc_temporalunit_label.gridy = 4;
		parent.add(temporalunit_label, gbc_temporalunit_label);

		temporalunit_combobox = new JComboBox();
		temporalunit_combobox.addItem("-");
		for (OWLNamedIndividual individual : getTemporalUnit()) {
			temporalunit_combobox.addItem(individual.getIRI().getFragment());
		}
		temporalunit_combobox.setSelectedIndex(0);
		GridBagConstraints gbc_temporalunit_combobox = new GridBagConstraints();
		gbc_temporalunit_combobox.insets = new Insets(0, 0, 5, 5);
		gbc_temporalunit_combobox.fill = GridBagConstraints.HORIZONTAL;
		gbc_temporalunit_combobox.gridx = 2;
		gbc_temporalunit_combobox.gridy = 4;
		parent.add(temporalunit_combobox, gbc_temporalunit_combobox);

	}

	public Object getBackPanelDescriptor() {
		return CreateConditionPage1.ID;
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	public Set<OWLNamedIndividual> getTemporalOperator() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#TemporalOperator"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Set<OWLNamedIndividual> getTemporalUnit() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(IRI
				.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#TemporalUnit"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}
}
