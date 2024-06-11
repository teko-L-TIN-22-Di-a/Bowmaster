package app.src.scenes;

import app.src.resources.StaticValues.SceneTag;

/**
 * Provides functionalities to switch between scenes.
 * 
 * @param _activeScene      Scene object currently used
 * @param _previousScene    Scene object used before
 * @param _newScene         Scene object to change to
 * @see Scene
 */
public class SceneHandler {
    private Scene _activeScene, _previousScene, _newScene;

    /**
     * Compares the TAGs from active and new scene.
     * 
     * @param newScene  Scene object to be compared
     * @return          boolean: true, if the new scene is different
     */
    public boolean sceneCheck(Scene newScene) {
        if (_activeScene.getTAG() != newScene.getTAG()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the activeScene  as previousScene 
     * and the newScene  as activeScene
     * 
     * @see setScene
     */
    public void startNew() {
        setScene(_activeScene, SceneTag.PREVIOUS);
        setScene(_newScene, SceneTag.ACTIVE);
    }

    /**
     * Sets the previousScene  as newScene 
     * and the activeScene  as previousScene,
     * then calls startNew() method.
     * 
     * @see setScene
     * @see startNew
     */
    public void startPrevious() {
        setScene(_previousScene, SceneTag.NEW);
        setScene(_activeScene, SceneTag.PREVIOUS);
        startNew();        
    }

    /**
     * Sets the provided Scene object to one of the Scene variables,
     * depending on the given SceneTag
     * 
     * @param scene Scene object which should be set
     * @param tag   SceneTag object, determines, which varaible is set.
     * @see Scene
     * @see SceneTag
     */
    public void setScene(Scene scene, SceneTag tag) {
        switch (tag) {
            case ACTIVE:
                _activeScene = scene;
                break;
            case PREVIOUS:
                _previousScene = scene;
                break;
            case NEW:
                _newScene = scene;
                break;
        }
    }

    /**
     * Returns the Scene object activeScene
     * 
     * @return Scene object activeScene
     */
    public Scene getActive() {
        return _activeScene;
    }

    /**
     * Returns the Scene object previousScene
     * 
     * @return Scene object previousScene
     */
    public Scene getPrevious() {
        return _previousScene;
    }

    /**
     * Returns the Scene object newScene
     * 
     * @return Scene object newScene
     */
    public Scene getNew() {
        return _newScene;
    }
}
