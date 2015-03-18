//-------By: David Craig | ID: w1354193-------\\
//------Bsc Computer Systems Engineering------\\

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>

void interrupt_handler (int signo);


int main()
{
	printf(" \n Key in C-c to Launch the program \n");

	int n;
	int pid, pid2;
	int status,i;
	int fd[2];
	int pipey2;
	int Childpipe1, Childpipe2, Childpipexor;
	const int MAXLINE = 4096;
	char line[MAXLINE];	
   
	//--------SIGNALS----------//
	signal(SIGTSTP, SIG_IGN); // ignore C-z when typed by user
	signal(SIGINT, interrupt_handler); // establish handler for C-c
	pause(); //go to sleep, waiting for signal
	//-------------------------/

  	if (pipe(fd) < 0)
	printf("Pipe Error");

	if ((pid = fork()) < 0)
	{
		printf("Fork Error");
	}
	else if (pid > 0)
	{
		
		if ((pid2 = fork()) < 0)
		{
			printf("Fork Error");
		}
		else if (pid2 > 0)
		{
		//Parent--------------------------------------------
			printf(" \n THIS IS THE PARENT:- \n GETPID: %d  GETPPID: %d  \n",        getpid(),getppid());
			close (fd[0]); // close the parents read end of the pipe
			close (fd[1]); // close the parents write end of the pipe
			
			//signal(SIGCHLD, interrupt_handler);
			wait(&status); // suspends execution of the calling process until a children terminates

			//-------CREATE FILE---------//
			FILE *fp;
			fp = fopen("OUTPUT.txt","w");
			fprintf(fp,"%s","Information \n"); /*writes data to the file*/
			sleep(1);
			
			//-----
			struct stat sb;
			int status;
			stat("OUTPUT.txt", &sb);

			
			fprintf(fp,"I-node number:            %ld\n", (long) sb.st_ino); // inode number

			fprintf(fp,"Mode:                     %lo (octal)\n",(unsigned long) sb.st_mode); // protection

			fprintf(fp,"Link count:               %ld\n", (long) sb.st_nlink); // number of hard links
			fprintf(fp,"Ownership:                UID=%ld   GID=%ld\n",(long) sb.st_uid, (long) sb.st_gid); // user and group ID of owner

			fprintf(fp,"Preferred I/O block size: %ld bytes\n",(long) sb.st_blksize); // blocksize for file system I/O
			fprintf(fp,"File size:                %lld bytes\n",(long long) sb.st_size); // total size, in bytes
			fprintf(fp,"Blocks allocated:         %lld\n",(long long) sb.st_blocks); // number of 512B blocks allocated

			fprintf(fp,"Last status change:       %s", ctime(&sb.st_ctime)); // time of last status change
			fprintf(fp,"Last file access:         %s", ctime(&sb.st_atime)); // time of last access
			fprintf(fp,"Last file modification:   %s", ctime(&sb.st_mtime)); // time of last modification 

			

			if (WIFEXITED(status)) {
					fprintf(fp,"Child exited, status=%d\n", WEXITSTATUS(status));
				    } else if (WIFSIGNALED(status)) {
					fprintf(fp,"Child killed by signal %d\n", WTERMSIG(status));
				    } else if (WIFSTOPPED(status)) {
					fprintf(fp,"Child stopped by signal %d\n", WSTOPSIG(status));
				    } else if (WIFCONTINUED(status)) {
					fprintf(fp,"continued\n");
				    }
			fclose(fp); /*finished*/

			}
			else
			{
			// CHILD 2--------------------------------------
	
			printf(" \n THIS IS THE CHILD 2:- \n GETPID: %d  GETPPID: %d \n", getpid(),getppid());

			dup2 (fd[0], STDIN_FILENO);
			close (fd[1]);
			scanf("%d ", &Childpipe1);
			Childpipe2 = ((rand() % 1000000000)+1);
			Childpipexor = (Childpipe1^Childpipe2); //xor the random numbers
			printf("%d\n", Childpipexor);
		}
	}
	else
	{
		// CHILD 1--------------------------------------		

		printf(" \n THIS IS THE CHILD 1:- \n GETPID: %d  GETPPID: %d  \n", getpid(),getppid());
			//random generator for child 1
		
		srand(time(NULL)); //seed in order to make a TRUE random number
		Childpipe1 = ((rand() % 1000000000)+1);
		dup2 (fd[1], STDOUT_FILENO);
		close(fd[0]);
		printf("%d\n", Childpipe1);

	}
    return 0;
}



void interrupt_handler (int signo) //signal handler
{

       printf("\nYou have permitted the program to take action \n");
}