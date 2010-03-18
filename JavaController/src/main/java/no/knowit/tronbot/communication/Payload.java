package no.knowit.tronbot.communication;

public enum Payload {
	LEFT('1'),
	RIGHT('2'),
	BACKWARD_LEFT('3'),
	BACKWARD_RIGHT('4'),
	FORWARD('5'),
	BACKWARD('6'),
	STOP('7');
	
	char command;
	
	Payload(char command) {
		this.command = command;
	}
	
	public String toString() {
		return Character.toString(command);
	}
	
}
