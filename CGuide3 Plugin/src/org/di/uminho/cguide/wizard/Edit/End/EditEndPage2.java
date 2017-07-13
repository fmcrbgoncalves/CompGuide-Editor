package org.di.uminho.cguide.wizard.Edit.End;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class EditEndPage2 extends AbstractOWLWizardPanel {

	public static final String ID = "EditEndPage2";

	public static final String title = "Insert End Data Required";

	private JLabel plan_description_label;

	public JTextArea plan_description_jtext;

	String generalDescription = new String();

	public EditEndPage2(OWLEditorKit owlEditorKit, String end_individual) {
		super(ID, title, owlEditorKit);
		getEnd(end_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the creation of the associated End Task.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		plan_description_label = new JLabel("Clinical End Description:");
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

	public Object getNextPanelDescriptor() {
		return EditEndPage3.ID;
	}

	@Override
	public void aboutToDisplayPanel() {
		plan_description_jtext.setText(this.generalDescription);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public void getEnd(String end_individual_name) {
		OWLNamedIndividual end_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + end_individual_name));

		try {
			OWLDataPropertyExpression generalDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#generalDescription"));
			Set<OWLLiteral> generalDescription_literal = end_individual
					.getDataPropertyValues(generalDescription_dataproperty, getOWLModelManager().getActiveOntology());

			this.generalDescription = generalDescription_literal.iterator().next().getLiteral();
		} catch (Exception e) {
		}
	}

}
