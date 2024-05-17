package Controllers;
import java.util.concurrent.atomic.AtomicBoolean;

import Helpers.QueueSingleton;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class SwitchButtonState implements Runnable {
	
private static SwitchButtonState _instance;
private AtomicBoolean buttonEnabled = new AtomicBoolean(false);
private QueueSingleton q = QueueSingleton.getSingleton();
	
	private SwitchButtonState() {}
	public static synchronized SwitchButtonState getSingleton() {
		if(_instance == null) {
			_instance = new SwitchButtonState();
		}
		return _instance;
	}
	
	private Button swapButton;
	
	public SwitchButtonState(Button swapButton) {
		this.swapButton = swapButton;
	}
	
	@Override
	public void run() {
		if(swapButton.isDisabled()) {
			swapButton.setDisable(false);
		} else {
			swapButton.setDisable(true);
		}
		
	}
	public AtomicBoolean getButtonState() {
		return buttonEnabled;
	}
	public void setButtonState(AtomicBoolean buttonEnabled) {
		this.buttonEnabled = buttonEnabled;
		
		if(q.queueSize()>0 && buttonEnabled.equals(true)) {
			//queue has been filled
			Platform.runLater(new SwitchButtonState());
			
		} else if(q.queueSize()>0 && buttonEnabled.equals(false)) {
			//clear queue
			q.clear();
			Platform.runLater(new SwitchButtonState());
		} else {
			
		}
	}

}
