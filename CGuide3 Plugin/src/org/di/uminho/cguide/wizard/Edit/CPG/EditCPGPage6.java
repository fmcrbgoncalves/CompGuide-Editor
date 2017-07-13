package org.di.uminho.cguide.wizard.Edit.CPG;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

public class EditCPGPage6 extends AbstractOWLWizardPanel {

	public static final String ID = "EditCPGPage6";

	public static final String title = "Insert Scope Data Required";

	private JLabel category_label;
	private JLabel added_label;

	private JList list_added;
	public DefaultListModel<String> model_added;

	private JList list_total;
	private DefaultListModel<String> model_total;

	private JButton btnAdd;
	private JButton btnRemove;

	public Set<OWLNamedIndividual> cpg_category, all_category;

	public EditCPGPage6(OWLEditorKit owlEditorKit, String cpg_individual) {
		super(ID, title, owlEditorKit);
		fixcpg_category(cpg_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions(
				"Please select the Guideline Category for this Clinical Practice Guideline and click in the Add button.\nYou can also select a content from the added list and remove it.");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 124, 95, 124, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 14, 77, 101, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		category_label = new JLabel("Clinical Category:");
		GridBagConstraints gbc_lblSpecialities = new GridBagConstraints();
		gbc_lblSpecialities.anchor = GridBagConstraints.NORTH;
		gbc_lblSpecialities.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecialities.gridx = 1;
		gbc_lblSpecialities.gridy = 1;
		parent.add(category_label, gbc_lblSpecialities);

		added_label = new JLabel("Added:");
		GridBagConstraints gbc_lblAdd = new GridBagConstraints();
		gbc_lblAdd.anchor = GridBagConstraints.NORTH;
		gbc_lblAdd.insets = new Insets(0, 0, 5, 0);
		gbc_lblAdd.gridx = 3;
		gbc_lblAdd.gridy = 1;
		parent.add(added_label, gbc_lblAdd);

		model_total = new DefaultListModel<String>();

		list_total = new JList();
		list_total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_total.setCellRenderer(getOWLEditorKit().getWorkspace().createOWLCellRenderer());
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
		list_added.setCellRenderer(getOWLEditorKit().getWorkspace().createOWLCellRenderer());
		list_added.setFixedCellHeight(20);
		list_added.setFixedCellWidth(100);

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

	}

	public Object getBackPanelDescriptor() {
		return EditCPGPage5.ID;
	}

	public Object getNextPanelDescriptor() {
		return EditCPGPage7.ID;
	}

	@Override
	public void aboutToDisplayPanel() {
		for (OWLNamedIndividual a : this.all_category)
			model_total.addElement(a.getIRI().getFragment());
		for (OWLNamedIndividual a : this.cpg_category)
			model_added.addElement(a.getIRI().getFragment());
		list_total.setModel(model_total);
		list_added.setModel(model_added);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Set<OWLNamedIndividual> getClinicalCategory() {
		Set<OWLIndividual> specialities = new HashSet<OWLIndividual>();
		Set<OWLNamedIndividual> clinicalspecialities = new HashSet<OWLNamedIndividual>();

		OWLOntology ontology = getOWLModelManager().getActiveOntology();

		OWLClass cs = getOWLModelManager().getOWLDataFactory().getOWLClass(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#GuidelineCategory"));

		specialities = cs.getIndividuals(ontology);

		for (OWLIndividual a : specialities) {
			clinicalspecialities.add(a.asOWLNamedIndividual());
		}

		return clinicalspecialities;
	}

	public Set<OWLNamedIndividual> getcpg_specialities(String cpg_individual_name) {

		Set<OWLNamedIndividual> res = new HashSet<OWLNamedIndividual>();

		OWLNamedIndividual cpg_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + cpg_individual_name));

		OWLObjectProperty scope_objectproperty = getOWLModelManager().getOWLDataFactory().getOWLObjectProperty(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#hasScope"));

		try {
			OWLNamedIndividual scope_individual = cpg_individual
					.getObjectPropertyValues(scope_objectproperty, getOWLModelManager().getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();

			OWLObjectProperty hasGuidelineCategory_objectproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLObjectProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#hasGuidelineCategory"));

			Set<OWLIndividual> clinicalcategories_set = scope_individual.getObjectPropertyValues(
					hasGuidelineCategory_objectproperty, getOWLModelManager().getActiveOntology());

			for (OWLIndividual a : clinicalcategories_set) {
				res.add(a.asOWLNamedIndividual());
			}
		} catch (Exception e) {
		}
		return res;
	}

	public void fixcpg_category(String cpg_individual) {
		this.cpg_category = getcpg_specialities(cpg_individual);
		this.all_category = getClinicalCategory();
		for (OWLNamedIndividual a : this.cpg_category) {
			try {
				this.all_category.remove(a);
			} catch (Exception e) {
			}
		}
	}

}
