package org.di.uminho.cguide.wizard.Edit.Plan;

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

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

public class EditPlanPage10 extends AbstractOWLWizardPanel {

	public static final String ID = "EditPlanPage10";

	public static final String title = "Plan Duration Restrictions";

	public int duration_type = 0;
	private JRadioButton maxminduration_ratiobutton;
	private JRadioButton duration_ratiobutton;
	public JTextField maxduration_textfield;
	public JTextField duration_textfield;
	private JLabel temporalunit_label;
	public JTextField minduration_textfield;
	public JComboBox temporalunit_combobox;
	private JLabel repetitionvalue_label;
	private JTextField repetitionvalue_textfield;

	public String maxduration = new String(), duration = new String(), temporalunit = new String(),
			minduration = new String(), repetitionvalue = new String();
	public int type = -1;

	public EditPlanPage10(OWLEditorKit owlEditorKit, String individual) {
		super(ID, title, owlEditorKit);
		getIndividual(individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert the duration of the Plan.");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 106, 5, 180, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 23, 0, 23, 0, 20, 20, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		maxminduration_ratiobutton = new JRadioButton("Max/Min Duration Restriction:", true);
		GridBagConstraints gbc_maxminduration_ratiobutton = new GridBagConstraints();
		gbc_maxminduration_ratiobutton.anchor = GridBagConstraints.EAST;
		gbc_maxminduration_ratiobutton.insets = new Insets(0, 0, 5, 5);
		gbc_maxminduration_ratiobutton.gridwidth = 2;
		gbc_maxminduration_ratiobutton.gridx = 0;
		gbc_maxminduration_ratiobutton.gridy = 0;
		parent.add(maxminduration_ratiobutton, gbc_maxminduration_ratiobutton);

		maxduration_textfield = new JTextField();
		maxduration_textfield.setColumns(10);
		maxduration_textfield.setEnabled(true);
		maxduration_textfield.setToolTipText("Enter Maximum Duration Restriction");
		GridBagConstraints gbc_maxduration_textfield = new GridBagConstraints();
		gbc_maxduration_textfield.insets = new Insets(0, 0, 5, 5);
		gbc_maxduration_textfield.gridx = 2;
		gbc_maxduration_textfield.gridy = 0;
		parent.add(maxduration_textfield, gbc_maxduration_textfield);

		minduration_textfield = new JTextField();
		minduration_textfield.setEnabled(true);
		minduration_textfield.setColumns(10);
		minduration_textfield.setToolTipText("Enter Minimum Duration Restriction");
		GridBagConstraints gbc_minduration_textfield = new GridBagConstraints();
		gbc_minduration_textfield.insets = new Insets(0, 0, 5, 5);
		gbc_minduration_textfield.gridx = 2;
		gbc_minduration_textfield.gridy = 1;
		parent.add(minduration_textfield, gbc_minduration_textfield);

		duration_ratiobutton = new JRadioButton("Average Duration Restriction:");
		GridBagConstraints gbc_duration_ratiobutton = new GridBagConstraints();
		gbc_duration_ratiobutton.anchor = GridBagConstraints.EAST;
		gbc_duration_ratiobutton.insets = new Insets(0, 0, 5, 5);
		gbc_duration_ratiobutton.gridwidth = 2;
		gbc_duration_ratiobutton.gridx = 0;
		gbc_duration_ratiobutton.gridy = 2;
		parent.add(duration_ratiobutton, gbc_duration_ratiobutton);

		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(maxminduration_ratiobutton);
		buttongroup.add(duration_ratiobutton);

		// Add Listener
		maxminduration_ratiobutton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// duration Restriction max/min -> type 0
					duration_type = 0;
					maxduration_textfield.setEnabled(true);
					minduration_textfield.setEnabled(true);
					duration_textfield.setEnabled(false);
					duration_textfield.setText("");
				}
			}
		});

		duration_ratiobutton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Average duration Restriction -> type 1
					duration_type = 1;
					maxduration_textfield.setEnabled(false);
					minduration_textfield.setEnabled(false);
					duration_textfield.setEnabled(true);
					maxduration_textfield.setText("");
					minduration_textfield.setText("");
				}
			}
		});

		duration_textfield = new JTextField();
		duration_textfield.setColumns(10);
		duration_textfield.setEnabled(false);
		GridBagConstraints gbc_duration_textfield = new GridBagConstraints();
		gbc_duration_textfield.insets = new Insets(0, 0, 5, 5);
		gbc_duration_textfield.gridx = 2;
		gbc_duration_textfield.gridy = 2;
		parent.add(duration_textfield, gbc_duration_textfield);

		temporalunit_label = new JLabel("Temporal Unit:");
		GridBagConstraints gbc_temporalunit_label = new GridBagConstraints();
		gbc_temporalunit_label.anchor = GridBagConstraints.EAST;
		gbc_temporalunit_label.gridwidth = 2;
		gbc_temporalunit_label.insets = new Insets(0, 0, 5, 5);
		gbc_temporalunit_label.gridx = 0;
		gbc_temporalunit_label.gridy = 3;
		parent.add(temporalunit_label, gbc_temporalunit_label);

		temporalunit_combobox = new JComboBox();
		temporalunit_combobox.addItem("-");
		for (OWLNamedIndividual individual : getTemporalUnit()) {
			temporalunit_combobox.addItem(individual.getIRI().getFragment());
		}
		temporalunit_combobox.setSelectedIndex(0);
		GridBagConstraints gbc_temporalunit_combobox = new GridBagConstraints();
		gbc_temporalunit_combobox.insets = new Insets(0, 0, 5, 5);
		gbc_temporalunit_combobox.gridx = 2;
		gbc_temporalunit_combobox.gridy = 3;
		parent.add(temporalunit_combobox, gbc_temporalunit_combobox);

	}

	public Object getBackPanelDescriptor() {
		return EditPlanPage9.ID;
	}

	public Object getNextPanelDescriptor() {
		return EditPlanPage11.ID;
	}

	@Override
	public void aboutToDisplayPanel() {
		if (this.type == 0) {
			maxduration_textfield.setText(this.maxduration);
			minduration_textfield.setText(this.minduration);

			maxminduration_ratiobutton.setSelected(true);

			maxduration_textfield.setEnabled(true);
			minduration_textfield.setEnabled(true);
			duration_textfield.setEnabled(false);

		} else if (this.type == 1) {
			duration_textfield.setText(this.duration);

			duration_ratiobutton.setSelected(true);

			maxduration_textfield.setEnabled(false);
			minduration_textfield.setEnabled(false);
			duration_textfield.setEnabled(true);
		}
		if (!(this.temporalunit.isEmpty())) {
			temporalunit_combobox.setSelectedItem(this.temporalunit);
		}
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public void getIndividual(String individual_name) {

		OWLNamedIndividual individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + individual_name));

		OWLObjectProperty hasDuration_objectproperty = getOWLModelManager().getOWLDataFactory().getOWLObjectProperty(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#hasDuration"));

		try {
			OWLNamedIndividual Duration_individual = individual
					.getObjectPropertyValues(hasDuration_objectproperty, getOWLModelManager().getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			OWLDataProperty maxDurationValue_dataproperty = getOWLModelManager().getOWLDataFactory().getOWLDataProperty(
					IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
							+ "#maxDurationValue"));

			try {
				OWLLiteral maxDurationValue_literal = Duration_individual
						.getDataPropertyValues(maxDurationValue_dataproperty, getOWLModelManager().getActiveOntology())
						.iterator().next();
				this.maxduration = maxDurationValue_literal.getLiteral();
				this.type = 0;
			} catch (Exception e) {
			}

			OWLDataProperty minDurationValue_dataproperty = getOWLModelManager().getOWLDataFactory().getOWLDataProperty(
					IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
							+ "#minDurationValue"));

			try {
				OWLLiteral minDurationValue_literal = Duration_individual
						.getDataPropertyValues(minDurationValue_dataproperty, getOWLModelManager().getActiveOntology())
						.iterator().next();
				this.minduration = minDurationValue_literal.getLiteral();
				this.type = 0;
			} catch (Exception e) {
			}

			OWLDataProperty durationValue_dataproperty = getOWLModelManager().getOWLDataFactory().getOWLDataProperty(
					IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
							+ "#durationValue"));

			try {
				OWLLiteral durationValue_literal = Duration_individual
						.getDataPropertyValues(durationValue_dataproperty, getOWLModelManager().getActiveOntology())
						.iterator().next();
				this.duration = durationValue_literal.getLiteral();
				this.type = 1;
			} catch (Exception e) {
			}

			OWLObjectProperty hasTemporalUnit_objectproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLObjectProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#hasTemporalUnit"));

			try {
				OWLNamedIndividual TemporalUnit_individual = Duration_individual
						.getObjectPropertyValues(hasTemporalUnit_objectproperty,
								getOWLModelManager().getActiveOntology())
						.iterator().next().asOWLNamedIndividual();
				this.temporalunit = TemporalUnit_individual.getIRI().getFragment();

			} catch (Exception e) {
			}

		} catch (Exception e) {
		}
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
