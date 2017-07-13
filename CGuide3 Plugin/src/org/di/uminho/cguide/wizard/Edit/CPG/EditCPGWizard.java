package org.di.uminho.cguide.wizard.Edit.CPG;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditCPGWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditCPGWizard.class);

	public EditCPGPage1 editcpgpage1;

	public EditCPGPage2 editcpgpage2;

	public EditCPGPage3 editcpgpage3;

	public EditCPGPage4 editcpgpage4;

	public EditCPGPage5 editcpgpage5;

	public EditCPGPage6 editcpgpage6;

	public EditCPGPage7 editcpgpage7;

	public EditCPGPage8 editcpgpage8;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditCPGWizard(OWLEditorKit editorKit, String cpg_individual) {
		setTitle("Clinical Protocol Guideline Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditCPGPage1.ID, editcpgpage1 = new EditCPGPage1(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage2.ID, editcpgpage2 = new EditCPGPage2(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage3.ID, editcpgpage3 = new EditCPGPage3(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage4.ID, editcpgpage4 = new EditCPGPage4(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage5.ID, editcpgpage5 = new EditCPGPage5(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage6.ID, editcpgpage6 = new EditCPGPage6(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage7.ID, editcpgpage7 = new EditCPGPage7(editorKit, cpg_individual));
		registerWizardPanel(EditCPGPage8.ID, editcpgpage8 = new EditCPGPage8(editorKit, cpg_individual));

		setCurrentPanel(EditCPGPage1.ID);
	}

}
