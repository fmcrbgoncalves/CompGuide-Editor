package org.di.uminho.cguide.wizard.Create.NonMedicationRecommendation;

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

public class CreateNonMedicationRecommendationPage extends AbstractOWLWizardPanel {

	public static final String ID = "CreateNonMedicationRecommendationPage";

	public static final String title = "Insert Non-Medication Recommendation Data Required";

	private JLabel nonmedicationrecommendation_description_label;
	private JLabel nonmedicationrecommendation_name_label;

	public JTextArea nonmedicationrecommendation_description_jtext;
	public JTextField nonmedicationrecommendation_name_jtext;

	public CreateNonMedicationRecommendationPage(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions(
				"Please insert data required for the creation of the associated Non-Medication Recommendation.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		nonmedicationrecommendation_name_label = new JLabel("Non-Medication Recommendation Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(nonmedicationrecommendation_name_label, gbc_lblName);

		nonmedicationrecommendation_name_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(nonmedicationrecommendation_name_jtext, gbc_textField);

		nonmedicationrecommendation_description_label = new JLabel("Non-Medication Recommendation Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		parent.add(nonmedicationrecommendation_description_label, gbc_lblDescription);

		nonmedicationrecommendation_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea1 = new GridBagConstraints();
		gbc_textArea1.fill = GridBagConstraints.BOTH;
		gbc_textArea1.gridx = 1;
		gbc_textArea1.gridy = 3;
		parent.add(new JScrollPane(nonmedicationrecommendation_description_jtext), gbc_textArea1);
	}

	public Object getBackPanelDescriptor() {
		return SelectClinicalActionPage.ID;
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

}
