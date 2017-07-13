package org.di.uminho.cguide.wizard.Edit.Parameter;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditParameterWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditParameterWizard.class);

	public EditParameterPage2 editparameterpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditParameterWizard(OWLEditorKit editorKit, String parameter_individual) {
		setTitle("Parameter Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditParameterPage2.ID,
				editparameterpage = new EditParameterPage2(editorKit, parameter_individual));

		setCurrentPanel(EditParameterPage2.ID);
	}

}
