package org.di.uminho.cguide.wizard.Create.Plan;

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
import org.semanticweb.owlapi.model.OWLOntology;

public class CreatePlanPage5 extends AbstractOWLWizardPanel {

	public static final String ID = "CreatePlanPage5";

	public static final String title = "Plan Stop Condition Task";

	private JLabel stopclinicaltask_label;
	private DefaultListModel<String> model_total;

	private JTextArea info_text;

	private Map<String, String> info_list;

	private JLabel info_label;
	public JList list_total;

	public CreatePlanPage5(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Stop Condition Tasks for the Plan (if there is one).");

		info_list = getDataClinicalTask(getClinicalTaskAction(), getClinicalTaskDecision(), getClinicalTaskEnd(),
				getClinicalTaskPlan(), getClinicalTaskQuestion());

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 130, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		stopclinicaltask_label = new JLabel("Clinical Task List:");
		GridBagConstraints gbc_stopclinicaltask_label = new GridBagConstraints();
		gbc_stopclinicaltask_label.anchor = GridBagConstraints.NORTH;
		gbc_stopclinicaltask_label.insets = new Insets(0, 0, 5, 5);
		gbc_stopclinicaltask_label.gridx = 1;
		gbc_stopclinicaltask_label.gridy = 1;
		parent.add(stopclinicaltask_label, gbc_stopclinicaltask_label);

		model_total = new DefaultListModel<String>();
		model_total.addElement("-");
		for (OWLNamedIndividual a : getClinicalTaskAction()) {
			model_total.addElement(a.getIRI().getFragment());
		}
		for (OWLNamedIndividual a : getClinicalTaskDecision()) {
			model_total.addElement(a.getIRI().getFragment());
		}
		for (OWLNamedIndividual a : getClinicalTaskEnd()) {
			model_total.addElement(a.getIRI().getFragment());
		}
		for (OWLNamedIndividual a : getClinicalTaskPlan()) {
			model_total.addElement(a.getIRI().getFragment());
		}
		for (OWLNamedIndividual a : getClinicalTaskQuestion()) {
			model_total.addElement(a.getIRI().getFragment());
		}

		info_label = new JLabel("Selected Clinical Task Info:");
		GridBagConstraints gbc_info_label1 = new GridBagConstraints();
		gbc_info_label1.insets = new Insets(0, 0, 5, 5);
		gbc_info_label1.gridx = 2;
		gbc_info_label1.gridy = 1;
		parent.add(info_label, gbc_info_label1);

		list_total = new JList();
		list_total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_total.setModel(model_total);
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
		return CreatePlanPage4.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreatePlanPage6.ID;
	}

	/*
	 * public Set<OWLNamedIndividual> getClinicalTaskAction() {
	 * Set<OWLNamedIndividual> conditions = new HashSet<OWLNamedIndividual>();
	 * Set<OWLNamedIndividual> res = new HashSet<OWLNamedIndividual>();
	 * OWLOntology ontology = getOWLModelManager().getActiveOntology();
	 * 
	 * OWLReasoner reasoner = getOWLModelManager().getReasoner();
	 * 
	 * OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
	 * IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().
	 * getOntologyIRI() + "#Action"));
	 * 
	 * NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cs, false);
	 * 
	 * conditions = instances.getFlattened();
	 * 
	 * try { // Inferencia do Reasoner apresenta Action Individuals, entre //
	 * outros, // nesta classe // Processo de correcao: for (OWLNamedIndividual
	 * a : conditions) { if
	 * (a.getIRI().getFragment().matches("^(Action|A|Workup){1}\\d+$")) {
	 * res.add(a); } } } catch (Exception e) { }
	 * 
	 * return res; }
	 */

	public Set<OWLNamedIndividual> getClinicalTaskAction() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#Action"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Set<OWLNamedIndividual> getClinicalTaskDecision() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#Decision"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	/*
	 * public Set<OWLNamedIndividual> getClinicalTaskEnd() {
	 * Set<OWLNamedIndividual> conditions = new HashSet<OWLNamedIndividual>();
	 * Set<OWLNamedIndividual> res = new HashSet<OWLNamedIndividual>();
	 * OWLOntology ontology = getOWLModelManager().getActiveOntology();
	 * 
	 * OWLReasoner reasoner = getOWLModelManager().getReasoner();
	 * 
	 * OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
	 * IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().
	 * getOntologyIRI() + "#End"));
	 * 
	 * NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cs, false);
	 * 
	 * conditions = instances.getFlattened();
	 * 
	 * try { // Inferencia do Reasoner apresenta Action Individuals, entre //
	 * outros, // nesta classe // Processo de correcao: for (OWLNamedIndividual
	 * a : conditions) { if (a.getIRI().getFragment().matches("^(End){1}\\d+$"))
	 * { res.add(a); } } } catch (Exception e) { }
	 * 
	 * return res; }
	 */

	public Set<OWLNamedIndividual> getClinicalTaskEnd() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#End"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Set<OWLNamedIndividual> getClinicalTaskPlan() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#Plan"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Set<OWLNamedIndividual> getClinicalTaskQuestion() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#Question"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Map<String, String> getDataClinicalTask(Set<OWLNamedIndividual> list_action,
			Set<OWLNamedIndividual> list_decision, Set<OWLNamedIndividual> list_end, Set<OWLNamedIndividual> list_plan,
			Set<OWLNamedIndividual> list_question) {
		Map<String, String> clinicaltasks = new HashMap<String, String>();

		// List ClinicalTask End
		for (OWLNamedIndividual individual : list_end) {
			try {

				String generalDescription = new String(); // String
				String info = new String();

				OWLDataPropertyExpression generalDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#generalDescription"));
				Set<OWLLiteral> generalDescription_literal = individual.getDataPropertyValues(
						generalDescription_dataproperty, getOWLModelManager().getActiveOntology());

				generalDescription = generalDescription_literal.iterator().next().getLiteral();

				info = "Clinical Task Type:End\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:" + generalDescription;

				clinicaltasks.put(individual.getIRI().getFragment(), info);

			} catch (Exception e) {
				String info = "Clinical Task Type:End\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:";
				clinicaltasks.put(individual.getIRI().getFragment(), info);
			}
		}

		// List ClinicalTask Action
		for (OWLNamedIndividual individual : list_action) {
			try {

				String generalDescription = new String(); // String
				String info = new String();

				OWLDataPropertyExpression generalDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#generalDescription"));
				Set<OWLLiteral> generalDescription_literal = individual.getDataPropertyValues(
						generalDescription_dataproperty, getOWLModelManager().getActiveOntology());

				generalDescription = generalDescription_literal.iterator().next().getLiteral();

				info = "Clinical Task Type:Action\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:" + generalDescription;

				clinicaltasks.put(individual.getIRI().getFragment(), info);

			} catch (Exception e) {
				String info = "Clinical Task Type:Action\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:";
				clinicaltasks.put(individual.getIRI().getFragment(), info);
			}
		}

		// List ClinicalTask Decision
		for (OWLNamedIndividual individual : list_decision) {
			try {

				String generalDescription = new String(); // String
				String info = new String();

				OWLDataPropertyExpression generalDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#generalDescription"));
				Set<OWLLiteral> generalDescription_literal = individual.getDataPropertyValues(
						generalDescription_dataproperty, getOWLModelManager().getActiveOntology());

				generalDescription = generalDescription_literal.iterator().next().getLiteral();

				info = "Clinical Task Type:Decision\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:" + generalDescription;

				clinicaltasks.put(individual.getIRI().getFragment(), info);

			} catch (Exception e) {
				String info = "Clinical Task Type:Decision\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:";
				clinicaltasks.put(individual.getIRI().getFragment(), info);
			}
		}

		// List ClinicalTask Plan
		for (OWLNamedIndividual individual : list_plan) {
			try {

				String generalDescription = new String(); // String
				String info = new String();

				OWLDataPropertyExpression generalDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#generalDescription"));
				Set<OWLLiteral> generalDescription_literal = individual.getDataPropertyValues(
						generalDescription_dataproperty, getOWLModelManager().getActiveOntology());

				generalDescription = generalDescription_literal.iterator().next().getLiteral();

				info = "Clinical Task Type:Plan\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:" + generalDescription;

				clinicaltasks.put(individual.getIRI().getFragment(), info);

			} catch (Exception e) {
				String info = "Clinical Task Type:Plan\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:";
				clinicaltasks.put(individual.getIRI().getFragment(), info);
			}
		}

		// List ClinicalTask Question
		for (OWLNamedIndividual individual : list_question) {
			try {

				String generalDescription = new String(); // String
				String info = new String();

				// Get Data Properties of each Condition
				OWLDataPropertyExpression generalDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
						.getOWLDataProperty(
								IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
										+ "#generalDescription"));
				Set<OWLLiteral> generalDescription_literal = individual.getDataPropertyValues(
						generalDescription_dataproperty, getOWLModelManager().getActiveOntology());

				generalDescription = generalDescription_literal.iterator().next().getLiteral();

				info = "Clinical Task Type:Question\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:" + generalDescription;

				clinicaltasks.put(individual.getIRI().getFragment(), info);

			} catch (Exception e) {
				String info = "Clinical Task Type:Question\nClinical Task ID:" + individual.getIRI().getFragment()
						+ "\nTask Description:";
				clinicaltasks.put(individual.getIRI().getFragment(), info);
			}
		}

		return clinicaltasks;
	}

}
