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
import org.semanticweb.owlapi.model.OWLOntology;

public class CreateActionPage9_4 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateActionPage9_4";

	public static final String title = "Medication Recommendation Clinical Action";

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

	public CreateActionPage9_4(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Medication Recommendations of the Action.");

		info_list = getDataClinicalAction(getClinicalActionMedicationRecommendation());

		last_selected_index_total = -1;
		last_selected_index_added = -1;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 95, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		condition_label = new JLabel("Medication Recommendation List:");
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
		for (OWLNamedIndividual a : getClinicalActionMedicationRecommendation()) {
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

		info_label = new JLabel("Selected Medication Recommendation Info:");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblInfo.gridx = 4;
		gbc_lblInfo.gridy = 1;
		parent.add(info_label, gbc_lblInfo);

		info_text = new JTextArea();
		info_text.setRows(5);
		info_text.setColumns(100);
		info_text.setEnabled(false);
		info_text.setCaretPosition(0);
		GridBagConstraints gbc_info_text = new GridBagConstraints();
		gbc_info_text.gridheight = 2;
		gbc_info_text.insets = new Insets(0, 0, 5, 0);
		gbc_info_text.fill = GridBagConstraints.BOTH;
		gbc_info_text.gridx = 4;
		gbc_info_text.gridy = 2;
		parent.add(new JScrollPane(info_text), gbc_info_text);

	}

	public Object getBackPanelDescriptor() {
		return CreateActionPage9.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreateActionPage10.ID;
	}

	/*
	 * public Set<OWLNamedIndividual>
	 * getClinicalActionMedicationRecommendation() { Set<OWLNamedIndividual>
	 * conditions = new HashSet<OWLNamedIndividual>(); Set<OWLNamedIndividual>
	 * res = new HashSet<OWLNamedIndividual>(); OWLOntology ontology =
	 * getOWLModelManager().getActiveOntology();
	 * 
	 * OWLReasoner reasoner = getOWLModelManager().getReasoner();
	 * 
	 * OWLClass cs = getOWLModelManager().getOWLDataFactory()
	 * .getOWLClass(IRI.create(getOWLModelManager().getActiveOntology().
	 * getOntologyID().getOntologyIRI() + "#MedicationRecommendation"));
	 * 
	 * NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cs, false);
	 * 
	 * conditions = instances.getFlattened();
	 * 
	 * try { for (OWLNamedIndividual a : conditions) { if
	 * (a.getIRI().getFragment().matches("^(CAT){1}\\d+$")) { res.add(a); } } }
	 * catch (Exception e) { }
	 * 
	 * return res; }
	 */

	public Set<OWLNamedIndividual> getClinicalActionMedicationRecommendation() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory()
				.getOWLClass(IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
						+ "#MedicationRecommendation"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Map<String, String> getDataClinicalAction(Set<OWLNamedIndividual> list_medicationrecommendation) {
		Map<String, String> clinicalexams = new HashMap<String, String>();

		// List ClinicalAction Exam
		for (OWLNamedIndividual individual : list_medicationrecommendation) {

			String ID = new String();
			String medicationrecommendationName = new String(); // String
			String activeIngredient = new String(); // String
			String posology = new String(); // String
			String dosage = new String(); // String
			String pharmaceuticalform = new String(); // String
			String medicationrecommendationDescription = new String(); // String
			String info = new String();

			try {
				OWLDataPropertyExpression medicationrecommendationName_dataproperty = getOWLModelManager()
						.getOWLDataFactory().getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#medicationRecommendationName"));
				Set<OWLLiteral> medicationrecommendationName_literal = individual.getDataPropertyValues(
						medicationrecommendationName_dataproperty, getOWLModelManager().getActiveOntology());

				medicationrecommendationName = medicationrecommendationName_literal.iterator().next().getLiteral();
			} catch (Exception e) {

			}
			try {

				OWLDataPropertyExpression activeIngredient_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#activeIngredient"));
				Set<OWLLiteral> activeIngredient_literal = individual
						.getDataPropertyValues(activeIngredient_dataproperty, getOWLModelManager().getActiveOntology());

				activeIngredient = activeIngredient_literal.iterator().next().getLiteral();

			} catch (Exception e) {

			}
			try {

				OWLDataPropertyExpression posology_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#posology"));
				Set<OWLLiteral> posology_literal = individual.getDataPropertyValues(posology_dataproperty,
						getOWLModelManager().getActiveOntology());

				posology = posology_literal.iterator().next().getLiteral();

			} catch (Exception e) {

			}
			try {
				OWLDataPropertyExpression dosage_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(IRI.create(
								getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#dosage"));
				Set<OWLLiteral> dosage_literal = individual.getDataPropertyValues(dosage_dataproperty,
						getOWLModelManager().getActiveOntology());

				dosage = dosage_literal.iterator().next().getLiteral();
			} catch (Exception e) {

			}
			try {
				OWLDataPropertyExpression pharmaceuticalform_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#pharmaceuticalForm"));
				Set<OWLLiteral> pharmaceuticalform_literal = individual.getDataPropertyValues(
						pharmaceuticalform_dataproperty, getOWLModelManager().getActiveOntology());

				pharmaceuticalform = pharmaceuticalform_literal.iterator().next().getLiteral();
			} catch (Exception e) {

			}
			try {
				OWLDataPropertyExpression medicationrecommendationDescription_dataproperty = getOWLModelManager()
						.getOWLDataFactory().getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#medicationRecommendationDescription"));
				Set<OWLLiteral> medicationrecommendationDescription_literal = individual.getDataPropertyValues(
						medicationrecommendationDescription_dataproperty, getOWLModelManager().getActiveOntology());

				medicationrecommendationDescription = medicationrecommendationDescription_literal.iterator().next()
						.getLiteral();
			} catch (Exception e) {

			}

			info = "Clinical Action Type:Medication Recommendation\nMedication Recommendation_ID:"
					+ individual.getIRI().getFragment() + "\nMedication Recommendation Name:"
					+ medicationrecommendationName + "\nActive Ingredient Name:" + activeIngredient + "\nPosology:"
					+ posology + "\nDosage:" + dosage + "\nPharmaceutical Form:" + pharmaceuticalform
					+ "\nMedication Recommendation Description:" + medicationrecommendationDescription;

			clinicalexams.put(individual.getIRI().getFragment(), info);
		}
		return clinicalexams;
	}

}
