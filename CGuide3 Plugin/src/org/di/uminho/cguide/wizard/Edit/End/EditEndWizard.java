package org.di.uminho.cguide.wizard.Edit.End;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditEndWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditEndWizard.class);

	public EditEndPage2 editendpage2;

	public EditEndPage3 editendpage3;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditEndWizard(OWLEditorKit editorKit, String end_individual) {
		setTitle("End Task Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditEndPage2.ID, editendpage2 = new EditEndPage2(editorKit, end_individual));
		registerWizardPanel(EditEndPage3.ID, editendpage3 = new EditEndPage3(editorKit, end_individual));

		setCurrentPanel(EditEndPage2.ID);
	}

}
