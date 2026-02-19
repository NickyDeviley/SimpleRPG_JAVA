package principal;

public final class GameLoop implements Runnable {

	/*
		A classe GameLoop tem a função de instanciar a janela e o painel,
		gerenciar o loop do jogo, contar o FPS e criar uma Thread para 
		rodar o GameLoop.
	*/
	
	// Declarando os objetos do projeto
	private GameWindow janela;
	private GamePanel painel;
	private Thread thread;
	private GameUI userInterface;
	
	// Declarando o limite do FPS e do update
	private final int FPS = 60;
	private final int UPS = 120;
	private final double NANOSSEGUNDOS_EM_SEGUNDO = 1000000000.0;
	
	// Variável que mantém o GameLoop
	private boolean running = false;
	
	// Método construtor
	public GameLoop() {
	
		// Instanciando objetos
		this.painel = new GamePanel();			// Instanciação do objeto Painel
		this.janela = new GameWindow(painel);	// Instanciação do objeto Janela passando o painel como parâmetro
		this.userInterface = this.painel.getUserInterface();
		
		// Chamando método
		this.startGameLoop();				// Método que instancia a Thread e inicia o GameLoop
		//this.userInterface.setGameThread(thread);
		//this.userInterface.setGameLoop(this);
	}

	/*
	  	O método run é uma função abstrata da classe Runnable,
	  	sua função é armazenar o código que será executado dentro da Thread
	*/
	@Override
	public void run() {
		
		// Este método armazena todas as variáveis e o GameLoop
		runningGameLoop();
		
	}
	
	// Método que inicia a thread e passa a instancia
	// da classe como parâmetro.
	private void startGameLoop() {
		
		/*
			A thread tem uma função simples, ela serve
			para manter o jogo fluído.
			
			Imagine vários carrinhos que precisam ser empurrados
			de um ponto A até um ponto B. Cada carrinho é uma coisa implementada
			no jogo, hitbox, comandos do jogador, gameloop, inteligência artificial das criaturas, etc.
			
			Seu processador empurrará cada carrinho até o ponto B, mas ele faz isso aos poucos,
			ele empurra o primeiro carrinho, para, depois empurra o segundo carrinho, para, e por ai vai.
			
			Quando utilizamos a Thread estamos dizendo: "este processo dentro da Thread será empurrado separadamente",
			ou seja, seu processador empurrará este carrinho independente dos outros, então ao invés de parar
			de checar as hitbox para recalcular o gameloop o processador continua movendo os carrinhos todos juntos.
			
			Quanto mais Threads forem usadas para cada processo, mais fluído será o jogo, porém mais
			processamento será exigido do seu computador.
		*/
		
		thread = new Thread(this);	// Instanciado a Thread e passando o objeto como parâmetro
		running = true;
		thread.start();				// Iniciando a Thread
	}
		
	private void runningGameLoop() {
		
		// Variáveis para controlar o GameLoop
		
		/*
		 	A primeira variável controla o tempo em que cada
		 	frame ficará desenhado na tela para o jogador
		 	Para maior precisão nós utilizamos Nanossegundos.
		
		 	1seg em nanossegundos é igual a 1bilhão, por 
		 	este motivo nós dividimos o FPS por 1bi. Pois
		 	nós queremos desenhar 60 imagens na tela e cada
		 	imagem deve permanecer tantos nanossegundos desenhada.
		 	
		 	A segunda variável determina quantas vezes por segundo a lógica
		 	movimentação, hitbox, etc. Vai ser checada e atualizada.
		*/
		double timePerFrame = NANOSSEGUNDOS_EM_SEGUNDO / FPS;
		double timePerUpdate = NANOSSEGUNDOS_EM_SEGUNDO / UPS;
		
		long timePassedFrame = 0;							// Armazena o tempo passado entre 1 frame e outro
		long timePassedUpdate = 0;							// Armazena o tempo passado entre 1 update e outro
		long actualTime = 0;								// Armazena o tempo atual do sistema em nanossegundos
		long lastCheck = System.currentTimeMillis();		// Armazena o momento em que o loop verificou se já podia redesenhar o frame
		long lastFrame = System.nanoTime();					// Armazena o momento em que o último frame foi desenhado
		long lastUpdate = System.nanoTime();				// Armazena o momento em que o último Update ocorreu
		int qtdFrames = 0;									// Armazena a quantidade de frames desenhadas em 1 segundo
		int qtdUpdates = 0;
		
		// Este é o "GameLoop", ele roda infinitamente até que o
		// jogo seja fechado.
		while(running) {
				
			if (this.userInterface.getGameFinished()) { stopGame(); }
			
			actualTime = System.nanoTime();						// Armazenando o tempo atual do sistema em Nanossegundos
			timePassedFrame = actualTime - lastFrame;			// Armazenando quanto tempo se passou desde o último frame Desenhado
			timePassedUpdate = actualTime - lastUpdate;			// Armazenando quanto tempo se passou desde o último Update
			
			// Operador de condição que verifica se já passou
			// o tempo necessário "timePerUpdate" para calcular
			// a lógica do jogo novamente
			if (timePassedUpdate >= timePerUpdate) {
				painel.update();
				lastUpdate += timePerUpdate;
				//lastUpdate = actualTime;
				qtdUpdates++;
			}
			
			// Operador de condição que verifica se já passou
			// o tempo necessário "timePerFrame" para desenhar
			// o próximo frame do jogo.
			if (timePassedFrame >= timePerFrame) {
				// Caso a condição seja verdadeira
				painel.repaint();							// Todo o conteúdo da tela é atualizado
				lastFrame += timePerFrame;
				//lastFrame = actualTime;					// O momento em que o último frame foi desenhado é atualiado
				qtdFrames++;								// Soma 1 a quantidade de frames já desenhada
			}
			else {
				// Caso a condição seja falsa
				
				// Calculamos e armazenamos o tempo que resta até o próximo frame
				long tempoRestanteFrame = (long) ((timePerFrame - timePassedFrame) / 1000000);
				long tempoRestanteUpdate = (long) ((timePerUpdate - timePassedUpdate) / 1000000);
				
				long timeToSleep = Math.min(tempoRestanteFrame, tempoRestanteUpdate);
						
				// Operador de condição para verificar se este tempo
				// calculado é maior que 0
				if (timeToSleep > 0) {
					// Caso a condição seja verdadeira
					
					try {
						Thread.sleep(timeToSleep);			// A thread "dorme" pelo tempo restante
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Aqui nós calculamos se o último check de FPS ocorreu a mais de 1 segundo
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				// Caso a condição seja satisfeita
				
				lastCheck = System.currentTimeMillis();			// Atualizamos o momento do último check
				//System.out.print("FPS: " + qtdFrames + " ");	// Imprimimos a quantidade de Frames em 1 segundo
				//System.out.println("UPS: " + qtdUpdates);		// Imrpimimos a quantidade de Updates em 1 segundo
				qtdFrames = 0;									// Resetamos a contagem de frames
				qtdUpdates = 0;									// Resetamos a contagem de updates
				
			}
		}
	}
	
	public void stopGame() {
		this.running = false;
		if (this.thread != null) {
			this.thread.interrupt();
			this.thread = null;
		}
	}
}
