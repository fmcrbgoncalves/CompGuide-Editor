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
import org.semanticweb.owlapi.model.OWLOntology;

public class EditCPGPage extends AbstractOWLWizardPanel {

	public static final String ID = "EditCPGPage";

	public static final String title = "Clinical Protocol Guideline Editor";

	private JLabel clinicaltask_label;
	private DefaultListModel<String> model_total;

	private JTextArea info_text;

	private Map<String, String> info_list;

	private JLabel info_label;
	public JList list_total;

	public EditCPGPage(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Clinical Protocol Guideline you wish to edit.");

		info_list = getDataCPG(getCPG());

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 130, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		clinicaltask_label = new JLabel("Clinical Protocol Guideline List:");
		GridBagConstraints gbc_stopclinicaltask_label = new GridBagConstraints();
		gbc_stopclinicaltask_label.anchor = GridBagConstraints.NORTH;
		gbc_stopclinicaltask_label.insets = new Insets(0, 0, 5, 5);
		gbc_stopclinicaltask_label.gridx = 1;
		gbc_stopclinicaltask_label.gridy = 1;
		parent.add(clinicaltask_label, gbc_stopclinicaltask_label);

		model_total = new DefaultListModel<String>();
		for (OWLNamedIndividual a : getCPG()) {
			model_total.addElement(a.getIRI().getFragment());
		}

		info_label = new JLabel("Selected Clinical Protocol Guideline Info:");
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
			return EditCPGPage.ID;
		else
			return WizardPanel.FINISH;
	}

	public Set<OWLNamedIndividual> getCPG() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory()
				.getOWLClass(IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
						+ "#ClinicalPracticeGuideline"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Map<String, String> getDataCPG(Set<OWLNamedIndividual> list) {
		Map<String, String> conditions = new HashMap<String, String>();

		for (OWLNamedIndividual individual : list) {

			String autorship = new String();
			String guidelineName = new String();
			String dateofCreation = new String();
			String dateofLastUpdate = new String();
			String guidelineDescription = new String();
			String versionNumber = new String();
			String info = new String();

			// Get Data Properties of each Condition
			Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = individual
					.getDataPropertyValues(getOWLModelManager().getActiveOntology());
			for (Map.Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data.entrySet()) {

				try {
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("authorship")) {
						autorship = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("guidelineName")) {
						guidelineName = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("dateOfCreation")) {
						dateofCreation = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("dateOfLastUpdate")) {
						dateofLastUpdate = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("guidelineDescription")) {
						guidelineDescription = entry.getValue().iterator().next().getLiteral();
					}
					if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("versionNumber")) {
						versionNumber = entry.getValue().iterator().next().getLiteral();
					}
				} catch (Exception e) {
				}
			}

			info = "CPG_ID:" + individual.getIRI().getFragment() + "\nGuideline Name:" + guidelineName + "\nAuthorship:"
					+ autorship + "\nVersion Number:" + versionNumber + "\nDate of Creation:" + dateofCreation
					+ "\nDate of Last Update:" + dateofLastUpdate;

			conditions.put(individual.getIRI().getFragment(), info);

		}

		return conditions;
	}

}
