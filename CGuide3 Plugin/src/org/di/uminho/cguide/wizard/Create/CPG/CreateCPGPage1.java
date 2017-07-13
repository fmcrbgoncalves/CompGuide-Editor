package org.di.uminho.cguide.wizard.Create.CPG;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.di.uminho.cguide.wizard.Create.SelectClassPage;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class CreateCPGPage1 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateCPGPage1";

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

	public CreateCPGPage1(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the creation of a Clinical Practice Guideline.");

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

	public Object getBackPanelDescriptor() {
		return SelectClassPage.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreateCPGPage2.ID;
	}

}
