def loadColor(){  
	BLUE_COLOR = "\033[1m\033[34m"
	YELLOW_COLOR = "\033[1m\033[43m"
	RED_COLOR = "\033[1m\033[31m"
	GREEN_COLOR = '\033[1m\033[32m'
}

def info(message) {
	loadColor()
	ansiColor('css') {
		echo "${BLUE_COLOR}" + "[INFO]: ${message}" + "${BLUE_COLOR}"
	}
}

def warning(message) {
    loadColor()
	ansiColor('css') {
		echo "${YELLOW_COLOR}" + "[WARNING]: ${message}" + "${YELLOW_COLOR}"
    }
}

def error(message) {
  loadColor()
  ansiColor('css') {
	echo "${RED_COLOR}" + "[ERROR]: ${message}" + "${RED_COLOR}"
  }
}

def success(message) {
  loadColor()
  ansiColor('css') {
	echo "${GREEN_COLOR}" + "[SUCCESS]: ${message}" + "${GREEN_COLOR}"
  }
}
