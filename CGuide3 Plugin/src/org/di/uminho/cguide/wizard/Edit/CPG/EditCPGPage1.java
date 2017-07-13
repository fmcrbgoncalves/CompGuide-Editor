package org.di.uminho.cguide.wizard.Edit.CPG;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class EditCPGPage1 extends AbstractOWLWizardPanel {

	public static final String ID = "EditCPGPage1";

	public static final String title = "Insert Clinical Practice Guideline Data Required";

	private JLabel autorship_label;
	private JLabel guidelineName_label;
	private JLabel guidelineDescription_label;

	// Version Number: Allways 1.0 at beginning;
	// Version Number is altered when Updating/Editing a CPG;

	public JTextField autorship_jtext;
	public JTextField guidelineName_jtext;
	public JTextArea guidelineDescription_jtext;

	private JLabel plan_description_label;
	public JTextArea plan_description_jtext;

	public String guidelineName = new String(), guidelineDescription = new String(), authorship = new String();

	public EditCPGPage1(OWLEditorKit owlEditorKit, String cpg_individual) {
		super(ID, title, owlEditorKit);
		getCPGData(cpg_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the edition of a Clinical Practice Guideline.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		guidelineName_label = new JLabel("Guideline Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(guidelineName_label, gbc_lblName);

		guidelineName_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(guidelineName_jtext, gbc_textField);

		autorship_label = new JLabel("Autorship:");
		GridBagConstraints gbc_lblAuthorship = new GridBagConstraints();
		gbc_lblAuthorship.anchor = GridBagConstraints.EAST;
		gbc_lblAuthorship.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthorship.gridx = 0;
		gbc_lblAuthorship.gridy = 3;
		parent.add(autorship_label, gbc_lblAuthorship);

		autorship_jtext = new JTextField(40);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		parent.add(autorship_jtext, gbc_textField_1);

		// box.add(autorship_label);
		// box.add(autorship_jtext);

		guidelineDescription_label = new JLabel("Guideline Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 5;
		parent.add(guidelineDescription_label, gbc_lblDescription);

		guidelineDescription_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 5;
		parent.add(new JScrollPane(guidelineDescription_jtext), gbc_textArea);

	}

	@Override
	public void aboutToDisplayPanel() {
		guidelineName_jtext.setText(this.guidelineName);
		autorship_jtext.setText(this.authorship);
		guidelineDescription_jtext.setText(this.guidelineDescription);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Object getNextPanelDescriptor() {
		return EditCPGPage2.ID;
	}

	public void getCPGData(String cpg_individual_name) {
		OWLNamedIndividual cpg_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + cpg_individual_name));

		// Get Data Properties of each Parameter
		Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = cpg_individual
				.getDataPropertyValues(getOWLModelManager().getActiveOntology());
		for (Map.Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data.entrySet()) {
			try {

				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("guidelineName")) {
					this.guidelineName = entry.getValue().iterator().next().getLiteral();
				} else if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("guidelineDescription")) {
					this.guidelineDescription = entry.getValue().iterator().next().getLiteral();
				} else if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("authorship")) {
					this.authorship = entry.getValue().iterator().next().getLiteral();
				}

			} catch (Exception e) {
			}
		}
	}

}
