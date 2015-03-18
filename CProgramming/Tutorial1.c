#include <stdio.h>
#include <unistd.h>

int main()
{
int n;
int fd[2];
 int pid, pid1;
const int MAXLINE = 4096;
char line[MAXLINE];

if (pipe(fd) < 0)
	printf("Pipe Error");
if ((pid = fork()) < 0)
{
	printf("Fork Error");
}
else if (pid > 0)
{
	close (fd[0]);
	write(fd[1], " Hello World\n", 12);

	printf(" \n THIS IS THE PARENT:- \n GETPID: %d  GETPPID: %d PID: %d \n",        getpid(),getppid(), pid);
	printf(" \n %d %d \n", fd[0], fd[1]);
	sleep(1);
	
}
else
{
	close (fd[1]);
	n = read(fd[0], line, MAXLINE);
	write (STDOUT_FILENO, line, n);

	printf(" \n THIS IS THE CHILD:- \n GETPID: %d  GETPPID: %d PID: %d \n", getpid(),getppid(), pid);
	printf(" \n %d %d \n", fd[0], fd[1]);
}
return 0;
}
