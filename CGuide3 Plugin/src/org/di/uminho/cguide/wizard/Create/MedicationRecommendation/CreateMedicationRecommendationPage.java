package org.di.uminho.cguide.wizard.Create.MedicationRecommendation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.di.uminho.cguide.wizard.Create.SelectClinicalActionPage;
import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class CreateMedicationRecommendationPage extends AbstractOWLWizardPanel {

	public static final String ID = "CreateMedicationRecommendationPage";

	public static final String title = "Insert Medication Recommendation Data Required";

	private JLabel medicationrecommendation_description_label;
	private JLabel medicationrecommendation_name_label;
	private JLabel medicationrecommendation_dosage_label;
	private JLabel medicationrecommendation_activeIngredient_label;
	private JLabel medicationrecommendation_pharmaceuticalForm_label;
	private JLabel medicationrecommendation_posology_label;

	public JTextArea medicationrecommendation_description_jtext;
	public JTextField medicationrecommendation_name_jtext;
	public JTextField medicationrecommendation_dosage_jtext;
	public JTextField medicationrecommendation_activeIngredient_jtext;
	public JTextField medicationrecommendation_pharmaceuticalForm_jtext;
	public JTextField medicationrecommendation_posology_jtext;

	public CreateMedicationRecommendationPage(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the creation of the associated Medication Recommendation.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		medicationrecommendation_name_label = new JLabel("Medication Recommendation Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(medicationrecommendation_name_label, gbc_lblName);

		medicationrecommendation_name_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(medicationrecommendation_name_jtext, gbc_textField);

		medicationrecommendation_activeIngredient_label = new JLabel("Active Ingredient Name:");
		GridBagConstraints gbc_lblName1 = new GridBagConstraints();
		gbc_lblName1.anchor = GridBagConstraints.EAST;
		gbc_lblName1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName1.gridx = 0;
		gbc_lblName1.gridy = 2;
		parent.add(medicationrecommendation_activeIngredient_label, gbc_lblName1);

		medicationrecommendation_activeIngredient_jtext = new JTextField(40);
		GridBagConstraints gbc_textField1 = new GridBagConstraints();
		gbc_textField1.insets = new Insets(0, 0, 5, 0);
		gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField1.gridx = 1;
		gbc_textField1.gridy = 2;
		parent.add(medicationrecommendation_activeIngredient_jtext, gbc_textField1);

		medicationrecommendation_posology_label = new JLabel("Posology:");
		GridBagConstraints gbc_lblName2 = new GridBagConstraints();
		gbc_lblName2.anchor = GridBagConstraints.EAST;
		gbc_lblName2.insets = new Insets(0, 0, 5, 5);
		gbc_lblName2.gridx = 0;
		gbc_lblName2.gridy = 3;
		parent.add(medicationrecommendation_posology_label, gbc_lblName2);

		medicationrecommendation_posology_jtext = new JTextField(40);
		GridBagConstraints gbc_textField2 = new GridBagConstraints();
		gbc_textField2.insets = new Insets(0, 0, 5, 0);
		gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField2.gridx = 1;
		gbc_textField2.gridy = 3;
		parent.add(medicationrecommendation_posology_jtext, gbc_textField2);

		medicationrecommendation_dosage_label = new JLabel("Dosage:");
		GridBagConstraints gbc_lblName3 = new GridBagConstraints();
		gbc_lblName3.anchor = GridBagConstraints.EAST;
		gbc_lblName3.insets = new Insets(0, 0, 5, 5);
		gbc_lblName3.gridx = 0;
		gbc_lblName3.gridy = 4;
		parent.add(medicationrecommendation_dosage_label, gbc_lblName3);

		medicationrecommendation_dosage_jtext = new JTextField(40);
		GridBagConstraints gbc_textField3 = new GridBagConstraints();
		gbc_textField3.insets = new Insets(0, 0, 5, 0);
		gbc_textField3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField3.gridx = 1;
		gbc_textField3.gridy = 4;
		parent.add(medicationrecommendation_dosage_jtext, gbc_textField3);

		medicationrecommendation_pharmaceuticalForm_label = new JLabel("Pharmaceutical Form:");
		GridBagConstraints gbc_lblName4 = new GridBagConstraints();
		gbc_lblName4.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblName4.insets = new Insets(0, 0, 5, 5);
		gbc_lblName4.gridx = 0;
		gbc_lblName4.gridy = 5;
		parent.add(medicationrecommendation_pharmaceuticalForm_label, gbc_lblName4);

		medicationrecommendation_pharmaceuticalForm_jtext = new JTextField(40);
		GridBagConstraints gbc_textField4 = new GridBagConstraints();
		gbc_textField4.insets = new Insets(0, 0, 5, 0);
		gbc_textField4.anchor = GridBagConstraints.NORTHEAST;
		gbc_textField4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4.gridx = 1;
		gbc_textField4.gridy = 5;
		parent.add(medicationrecommendation_pharmaceuticalForm_jtext, gbc_textField4);

		medicationrecommendation_description_label = new JLabel("Medication Recommendation Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 6;
		parent.add(medicationrecommendation_description_label, gbc_lblDescription);

		medicationrecommendation_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea1 = new GridBagConstraints();
		gbc_textArea1.fill = GridBagConstraints.BOTH;
		gbc_textArea1.anchor = GridBagConstraints.NORTHEAST;
		gbc_textArea1.gridx = 1;
		gbc_textArea1.gridy = 6;
		parent.add(new JScrollPane(medicationrecommendation_description_jtext), gbc_textArea1);
	}

	public Object getBackPanelDescriptor() {
		return SelectClinicalActionPage.ID;
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

}
