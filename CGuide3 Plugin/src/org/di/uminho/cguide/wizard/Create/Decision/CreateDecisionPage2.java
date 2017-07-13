package org.di.uminho.cguide.wizard.Create.Decision;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.di.uminho.cguide.wizard.Create.SelectClinicalTaskPage;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class CreateDecisionPage2 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateDecisionPage2";

	public static final String title = "Insert Decision Data Required";

	private JLabel plan_description_label;

	public JTextArea plan_description_jtext;

	public CreateDecisionPage2(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the creation of the associated Decision.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		plan_description_label = new JLabel("Clinical Decision Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		parent.add(plan_description_label, gbc_lblDescription);

		plan_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		parent.add(new JScrollPane(plan_description_jtext), gbc_textArea);

	}

	public Object getBackPanelDescriptor() {
		return SelectClinicalTaskPage.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreateDecisionPage3.ID;
	}

}
