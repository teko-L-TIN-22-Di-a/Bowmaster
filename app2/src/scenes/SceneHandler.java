package app2.src.scenes;

import app2.src.resources.StaticValues.SceneTag;

public class SceneHandler {
    private Scene _activeScene, _previousScene, _newScene;

    public boolean sceneCheck(Scene newScene) {
        if (_activeScene.getTAG() != newScene.getTAG()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void startNew() {
        setScene(_activeScene, SceneTag.PREVIOUS);
        setScene(_newScene, SceneTag.ACTIVE);
    }

    public void startPrevious() {
        setScene(_previousScene, SceneTag.NEW);
        setScene(_activeScene, SceneTag.PREVIOUS);
        startNew();        
    }

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

    public Scene getActive() {
        return _activeScene;
    }

    public Scene getPrevious() {
        return _previousScene;
    }

    public Scene getNew() {
        return _newScene;
    }
}
