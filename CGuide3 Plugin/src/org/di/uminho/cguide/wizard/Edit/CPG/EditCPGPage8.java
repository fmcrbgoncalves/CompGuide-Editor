package org.di.uminho.cguide.wizard.Edit.CPG;

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
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

public class EditCPGPage8 extends AbstractOWLWizardPanel {

	public static final String ID = "EditCPGPage8";

	public static final String title = "Clinical Guideline Plan";

	private JLabel clinicaltask_label;
	private DefaultListModel<String> model_total;

	private JTextArea info_text;

	private Map<String, String> info_list;

	private JLabel info_label;
	public JList list_total;

	public String cpg_plan = new String();

	public EditCPGPage8(OWLEditorKit owlEditorKit, String cpg_individual) {
		super(ID, title, owlEditorKit);
		getCPGPlan(cpg_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the associated Plan for the Clinical Guideline.");

		info_list = getDataPlans(getPlans());

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 130, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		clinicaltask_label = new JLabel("Plan Task List:");
		GridBagConstraints gbc_stopclinicaltask_label = new GridBagConstraints();
		gbc_stopclinicaltask_label.anchor = GridBagConstraints.NORTH;
		gbc_stopclinicaltask_label.insets = new Insets(0, 0, 5, 5);
		gbc_stopclinicaltask_label.gridx = 1;
		gbc_stopclinicaltask_label.gridy = 1;
		parent.add(clinicaltask_label, gbc_stopclinicaltask_label);

		model_total = new DefaultListModel<String>();
		model_total.addElement("-");
		for (OWLNamedIndividual a : getPlans()) {
			model_total.addElement(a.getIRI().getFragment());
		}

		info_label = new JLabel("Selected Plan Task Info:");
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
		return EditCPGPage7.ID;
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	@Override
	public void aboutToDisplayPanel() {
		if (!(this.cpg_plan.isEmpty())) {
			list_total.setSelectedValue(this.cpg_plan, true);
		}
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Set<OWLNamedIndividual> getPlans() {
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

	public Map<String, String> getDataPlans(Set<OWLNamedIndividual> list) {
		Map<String, String> clinicaltasks = new HashMap<String, String>();

		// List ClinicalTask Plan
		for (OWLNamedIndividual individual : list) {
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

		return clinicaltasks;
	}

	public void getCPGPlan(String cpg_individual_name) {
		OWLNamedIndividual cpg_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + cpg_individual_name));

		OWLObjectProperty hasPlan_objectproperty = getOWLModelManager().getOWLDataFactory().getOWLObjectProperty(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#hasPlan"));

		try {
			OWLNamedIndividual plan_individual = cpg_individual
					.getObjectPropertyValues(hasPlan_objectproperty, getOWLModelManager().getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			this.cpg_plan = plan_individual.getIRI().getFragment();

		} catch (Exception e) {
		}
	}
}
