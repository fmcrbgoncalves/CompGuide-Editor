package org.coode.dlquery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.InputVerificationStatusChangedListener;
import org.protege.editor.owl.model.cache.OWLExpressionUserCache;
import org.protege.editor.owl.model.entity.OWLEntityCreationSet;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerChangeEvent;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.protege.editor.owl.model.inference.OWLReasonerManager;
import org.protege.editor.owl.model.inference.ReasonerUtilities;
import org.protege.editor.owl.ui.CreateDefinedClassPanel;
import org.protege.editor.owl.ui.clsdescriptioneditor.ExpressionEditor;
import org.protege.editor.owl.ui.clsdescriptioneditor.OWLExpressionChecker;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntologyChange;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: 22-Aug-2006<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLClassExpressionEditorViewComponent extends AbstractOWLViewComponent {
    private static final long serialVersionUID = 8268241587271333587L;

    Logger log = Logger.getLogger(OWLClassExpressionEditorViewComponent.class);

    private ExpressionEditor<OWLClassExpression> owlDescriptionEditor;

    private ResultsList resultsList;

    private JCheckBox showSuperClassesCheckBox;

    private JCheckBox showAncestorClassesCheckBox;

    private JCheckBox showEquivalentClassesCheckBox;

    private JCheckBox showSubClassesCheckBox;

    private JCheckBox showDescendantClassesCheckBox;

    private JCheckBox showIndividualsCheckBox;

    private JButton executeButton;

    private JButton addButton;

    private OWLModelManagerListener listener;

    private boolean requiresRefresh = false;


    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout(10, 10));

        JComponent editorPanel = createQueryPanel();
        JComponent resultsPanel = createResultsPanel();
        JComponent optionsBox = createOptionsBox();
        resultsPanel.add(optionsBox, BorderLayout.EAST);

        JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorPanel, resultsPanel);
        splitter.setDividerLocation(0.3);

        add(splitter, BorderLayout.CENTER);

        updateGUI();

        listener = new OWLModelManagerListener() {
            public void handleChange(OWLModelManagerChangeEvent event) {
                if (event.isType(EventType.ONTOLOGY_CLASSIFIED)) {
                    doQuery();
                }
            }
        };

        getOWLModelManager().addListener(listener);

        addHierarchyListener(new HierarchyListener(){
            public void hierarchyChanged(HierarchyEvent event) {
                if (requiresRefresh && isShowing()){
                    doQuery();
                }
            }
        });
    }


    private JComponent createQueryPanel() {
        JPanel editorPanel = new JPanel(new BorderLayout());

        final OWLExpressionChecker<OWLClassExpression> checker = getOWLModelManager().getOWLExpressionCheckerFactory().getOWLClassExpressionChecker();
        owlDescriptionEditor = new ExpressionEditor<OWLClassExpression>(getOWLEditorKit(), checker);
        owlDescriptionEditor.addStatusChangedListener(new InputVerificationStatusChangedListener(){
            public void verifiedStatusChanged(boolean newState) {
                executeButton.setEnabled(newState);
                addButton.setEnabled(newState);
            }
        });
        owlDescriptionEditor.setPreferredSize(new Dimension(100, 50));

        editorPanel.add(ComponentFactory.createScrollPane(owlDescriptionEditor), BorderLayout.CENTER);
        JPanel buttonHolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
        executeButton = new JButton(new AbstractAction("Execute") {
            /**
             * 
             */
            private static final long serialVersionUID = -1833321282125901561L;

            public void actionPerformed(ActionEvent e) {
                doQuery();
            }
        });

        addButton = new JButton(new AbstractAction("Add to ontology"){
            /**
             * 
             */
            private static final long serialVersionUID = -6050625862820344594L;

            public void actionPerformed(ActionEvent event) {
                doAdd();
            }
        });
        buttonHolder.add(executeButton);
        buttonHolder.add(addButton);

        editorPanel.add(buttonHolder, BorderLayout.SOUTH);
        editorPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Color.LIGHT_GRAY), "Query (class expression)"), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        return editorPanel;
    }


    private JComponent createResultsPanel() {
        JComponent resultsPanel = new JPanel(new BorderLayout(10, 10));
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Color.LIGHT_GRAY), "Query results"), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        resultsList = new ResultsList(getOWLEditorKit());
        resultsList.setShowSubClasses(true);
        resultsPanel.add(ComponentFactory.createScrollPane(resultsList));
        return resultsPanel;
    }


    private JComponent createOptionsBox() {
        Box optionsBox = new Box(BoxLayout.Y_AXIS);
        showSuperClassesCheckBox = new JCheckBox(new AbstractAction("Super classes") {
            /**
             * 
             */
            private static final long serialVersionUID = 1531417504526875891L;

            public void actionPerformed(ActionEvent e) {
                resultsList.setShowSuperClasses(showSuperClassesCheckBox.isSelected());
                doQuery();
            }
        });
        optionsBox.add(showSuperClassesCheckBox);
        optionsBox.add(Box.createVerticalStrut(3));

        showAncestorClassesCheckBox = new JCheckBox(new AbstractAction("Ancestor classes") {
            /**
             * 
             */
            private static final long serialVersionUID = 4603049796331219219L;

            public void actionPerformed(ActionEvent e) {
                resultsList.setShowAncestorClasses(showAncestorClassesCheckBox.isSelected());
                doQuery();
            }
        });
        showAncestorClassesCheckBox.setSelected(false);
        optionsBox.add(showAncestorClassesCheckBox);
        optionsBox.add(Box.createVerticalStrut(3));

        showEquivalentClassesCheckBox = new JCheckBox(new AbstractAction("Equivalent classes") {
            /**
             * 
             */
            private static final long serialVersionUID = -3766966095409342054L;

            public void actionPerformed(ActionEvent e) {
                resultsList.setShowEquivalentClasses(showEquivalentClassesCheckBox.isSelected());
                doQuery();
            }
        });
        optionsBox.add(showEquivalentClassesCheckBox);
        optionsBox.add(Box.createVerticalStrut(3));

        showSubClassesCheckBox = new JCheckBox(new AbstractAction("Subclasses") {
            private static final long serialVersionUID = 696913194074753412L;

            public void actionPerformed(ActionEvent e) {
                resultsList.setShowSubClasses(showSubClassesCheckBox.isSelected());
                doQuery();
            }
        });
        optionsBox.add(showSubClassesCheckBox);
        optionsBox.add(Box.createVerticalStrut(3));

        showDescendantClassesCheckBox = new JCheckBox(new AbstractAction("Descendant classes") {
            private static final long serialVersionUID = -3418802363566640471L;

            public void actionPerformed(ActionEvent e) {
                resultsList.setShowDescendantClasses(showDescendantClassesCheckBox.isSelected());
                doQuery();
            }
        });
        showDescendantClassesCheckBox.setSelected(false);
        optionsBox.add(showDescendantClassesCheckBox);
        optionsBox.add(Box.createVerticalStrut(3));

        showIndividualsCheckBox = new JCheckBox(new AbstractAction("Individuals") {
            /**
             * 
             */
            private static final long serialVersionUID = -7727032635999833150L;

            public void actionPerformed(ActionEvent e) {
                resultsList.setShowInstances(showIndividualsCheckBox.isSelected());
                doQuery();
            }
        });
        optionsBox.add(showIndividualsCheckBox);

        return optionsBox;
    }


    protected void disposeOWLView() {
        getOWLModelManager().removeListener(listener);
    }


    private void updateGUI() {
        showSuperClassesCheckBox.setSelected(resultsList.isShowSuperClasses());
        showAncestorClassesCheckBox.setSelected(resultsList.isShowAncestorClasses());
        showEquivalentClassesCheckBox.setSelected(resultsList.isShowEquivalentClasses());
        showSubClassesCheckBox.setSelected(resultsList.isShowSubClasses());
        showDescendantClassesCheckBox.setSelected(resultsList.isShowDescendantClasses());
        showIndividualsCheckBox.setSelected(resultsList.isShowInstances());
    }


    private void doQuery() {
        if (isShowing()){
            try {
            	OWLReasonerManager reasonerManager = getOWLModelManager().getOWLReasonerManager();
            	ReasonerUtilities.warnUserIfReasonerIsNotConfigured(this, reasonerManager);

                OWLClassExpression desc = owlDescriptionEditor.createObject();
                if (desc != null){
                    OWLExpressionUserCache.getInstance(getOWLModelManager()).add(desc, owlDescriptionEditor.getText());
                    resultsList.setOWLClassExpression(desc);
                }
            }
            catch (OWLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Exception caught trying to do the query", e);
                }
            }
            requiresRefresh = false;
        }
        else{
            requiresRefresh = true;
        }
    }


    private void doAdd() {
        try {
            OWLClassExpression desc = owlDescriptionEditor.createObject();
            OWLEntityCreationSet<OWLClass> creationSet = CreateDefinedClassPanel.showDialog(desc, getOWLEditorKit());
            if (creationSet != null) {
            	List<OWLOntologyChange> changes = new ArrayList<OWLOntologyChange>(creationSet.getOntologyChanges());
            	OWLDataFactory factory = getOWLModelManager().getOWLDataFactory();
            	OWLAxiom equiv = factory.getOWLEquivalentClassesAxiom(creationSet.getOWLEntity(), desc);
            	changes.add(new AddAxiom(getOWLModelManager().getActiveOntology(), equiv));
                getOWLModelManager().applyChanges(changes);
                if (isSynchronizing()){
                    getOWLEditorKit().getOWLWorkspace().getOWLSelectionModel().setSelectedEntity(creationSet.getOWLEntity());    
                }
            }
        }
        catch (OWLException e) {
            if (log.isDebugEnabled()){
                log.debug("Exception caught trying to parse DL query", e);
            }
        }
    }
}
