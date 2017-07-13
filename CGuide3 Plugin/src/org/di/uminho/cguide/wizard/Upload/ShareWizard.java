package org.di.uminho.cguide.wizard.Upload;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class ShareWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(ShareWizard.class);

	public UploadPage uploadpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public ShareWizard(OWLEditorKit editorKit) {
		setTitle("Deletion Wizard");
		this.owlModelManager = editorKit.getModelManager();
		registerWizardPanel(UploadPage.ID, uploadpage = new UploadPage(editorKit));

		setCurrentPanel(UploadPage.ID);

	}
}
