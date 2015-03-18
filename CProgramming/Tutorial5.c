#include <stdio.h>
#include <signal.h>

static void sig_usr(int); // one handler for both signals 

int main()
{
	if (signal(SIGUSR1, sig_usr) == SIG_ERR)
		printf("can't catch SIGUSR1");
	if (signal(SIGUSR2, sig_usr) == SIG_ERR)
		printf("can't catch SIGUSR2");
	pause();
	
}
static void
sig_usr(int signo) //argument is signal number
{
		if (signo == SIGUSR1)
			printf("received SIGUSR1\n");
		else if (signo == SIGUSR2)
			printf("recieved SIGUSR2\n");
		else
			printf("recieved signal %d\n", signo);
	}
