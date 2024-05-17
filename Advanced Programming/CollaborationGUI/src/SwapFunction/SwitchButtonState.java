package SwapFunction;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.control.Button;

public class SwitchButtonState implements Runnable {
	
private AtomicBoolean buttonEnabled = new AtomicBoolean(false);
	
	private Button swapSuggetionButton;
	
	public SwitchButtonState(Button swapSuggestionButton) {
		this.swapSuggetionButton = swapSuggestionButton;
	}
	
	@Override
	public void run() {
		if(swapSuggetionButton.isDisabled()) {
			swapSuggetionButton.setDisable(false);
		} else {
			swapSuggetionButton.setDisable(true);
		}
		
	}
	public AtomicBoolean getButtonState() {
		return buttonEnabled;
	}
	public void setButtonState(AtomicBoolean buttonEnabled) {
		this.buttonEnabled = buttonEnabled;
	}

}
