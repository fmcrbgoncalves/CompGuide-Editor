package org.di.uminho.cguide.wizard.Edit.Question;

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

import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

public class EditQuestionPage8preferencealternative extends AbstractOWLWizardPanel {

	public static final String ID = "EditQuestionPage8preferencealternative";

	public static final String title = "Question Preference Alternative Clinical Tasks";

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

	public Set<OWLNamedIndividual> all_conditions, individual_conditions;

	public EditQuestionPage8preferencealternative(OWLEditorKit owlEditorKit, String individual) {
		super(ID, title, owlEditorKit);
		fix(individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select at least 2 Preference Alternative Clinical Tasks for the Question.");

		info_list = getDataClinicalTask(getClinicalTaskAction(), getClinicalTaskDecision(), getClinicalTaskEnd(),
				getClinicalTaskPlan(), getClinicalTaskQuestion());

		last_selected_index_total = -1;
		last_selected_index_added = -1;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 95, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		condition_label = new JLabel("Clinical Task List:");
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
		/*
		 * for (OWLNamedIndividual a : getClinicalTaskAction()) {
		 * model_total.addElement(a.getIRI().getFragment()); } for
		 * (OWLNamedIndividual a : getClinicalTaskDecision()) {
		 * model_total.addElement(a.getIRI().getFragment()); } for
		 * (OWLNamedIndividual a : getClinicalTaskEnd()) {
		 * model_total.addElement(a.getIRI().getFragment()); } for
		 * (OWLNamedIndividual a : getClinicalTaskPlan()) {
		 * model_total.addElement(a.getIRI().getFragment()); } for
		 * (OWLNamedIndividual a : getClinicalTaskQuestion()) {
		 * model_total.addElement(a.getIRI().getFragment()); }
		 */

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

		info_label = new JLabel("Selected Clinical Task Info:");
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
		return EditQuestionPage7.ID;
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	@Override
	public void aboutToDisplayPanel() {
		for (OWLNamedIndividual a : this.all_conditions) {
			model_total.addElement(a.getIRI().getFragment());
		}
		for (OWLNamedIndividual a : this.individual_conditions) {
			model_added.addElement(a.getIRI().getFragment());
		}
		list_total.setModel(model_total);
		list_added.setModel(model_added);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Set<OWLNamedIndividual> getIndividual(String individual_name) {

		Set<OWLNamedIndividual> res = new HashSet<OWLNamedIndividual>();

		OWLNamedIndividual individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + individual_name));

		OWLObjectProperty alternativeTask_objectproperty = getOWLModelManager().getOWLDataFactory()
				.getOWLObjectProperty(
						IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
								+ "#preferenceAlternativeTask"));

		try {
			Set<OWLIndividual> alternativeTask_individual = individual
					.getObjectPropertyValues(alternativeTask_objectproperty, getOWLModelManager().getActiveOntology());

			for (OWLIndividual a : alternativeTask_individual) {
				res.add(a.asOWLNamedIndividual());
			}
		} catch (Exception e) {
		}
		return res;
	}

	public void fix(String individual) {
		this.all_conditions = getClinicalTaskAction();
		this.all_conditions.addAll(getClinicalTaskDecision());
		this.all_conditions.addAll(getClinicalTaskEnd());
		this.all_conditions.addAll(getClinicalTaskPlan());
		this.all_conditions.addAll(getClinicalTaskQuestion());

		this.individual_conditions = getIndividual(individual);
		for (OWLNamedIndividual a : this.individual_conditions) {
			this.all_conditions.remove(a);
		}
	}

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
				String ID = new String();
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
				String ID = new String();
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
				String ID = new String();
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
				String ID = new String();
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
				String ID = new String();
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
