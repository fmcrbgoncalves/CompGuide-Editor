package org.di.uminho.cguide.wizard.Edit.Procedure;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class EditProcedurePage extends AbstractOWLWizardPanel {

	public static final String ID = "EditProcedurePage";

	public static final String title = "Insert Procedure Data Required";

	private JLabel procedure_description_label;
	private JLabel procedure_name_label;

	public JTextArea procedure_description_jtext;
	public JTextField procedure_name_jtext;

	String procedureName = new String(), procedureDescription = new String();

	public EditProcedurePage(OWLEditorKit owlEditorKit, String procedure_individual) {
		super(ID, title, owlEditorKit);
		getProcedure(procedure_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the edition of the associated Procedure.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		procedure_name_label = new JLabel("Procedure Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(procedure_name_label, gbc_lblName);

		procedure_name_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(procedure_name_jtext, gbc_textField);

		procedure_description_label = new JLabel("Procedure Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		parent.add(procedure_description_label, gbc_lblDescription);

		procedure_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea1 = new GridBagConstraints();
		gbc_textArea1.fill = GridBagConstraints.BOTH;
		gbc_textArea1.gridx = 1;
		gbc_textArea1.gridy = 3;
		parent.add(new JScrollPane(procedure_description_jtext), gbc_textArea1);
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	@Override
	public void aboutToDisplayPanel() {
		procedure_name_jtext.setText(this.procedureName);
		procedure_description_jtext.setText(this.procedureDescription);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public void getProcedure(String procedure_individual_name) {
		OWLNamedIndividual procedure_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#"
						+ procedure_individual_name));

		try {
			OWLDataPropertyExpression procedureName_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#procedureName"));
			Set<OWLLiteral> procedureName_literal = procedure_individual
					.getDataPropertyValues(procedureName_dataproperty, getOWLModelManager().getActiveOntology());

			this.procedureName = procedureName_literal.iterator().next().getLiteral();
		} catch (Exception e) {
		}
		try {
			OWLDataPropertyExpression procedureDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#procedureDescription"));
			Set<OWLLiteral> procedureDescription_literal = procedure_individual
					.getDataPropertyValues(procedureDescription_dataproperty, getOWLModelManager().getActiveOntology());

			this.procedureDescription = procedureDescription_literal.iterator().next().getLiteral();

		} catch (Exception e) {
		}
	}

}
