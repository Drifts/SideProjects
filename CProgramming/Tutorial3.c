#include <stdio.h>
#include <unistd.h>

int main()
{
int n;
int fd[2];
int pid;
const int MAXLINE = 4096;
char line[MAXLINE];
int pipey2;
char bacon;

if (pipe(fd) < 0)
	printf("Pipe Error");
if ((pid = fork()) < 0)
{
	printf("Fork Error");
}
else if (pid > 0)
{	
	pipey2 = dup2 (fd[1], STDOUT_FILENO);
	printf("%d %d", fd[1], STDOUT_FILENO);
	close (fd[0]);
	write(STDOUT_FILENO, " \n Hello World\n", 14);
	printf (" hello stranger ");
	printf(" \nParent Finished \n");
}
else
{
	pipey2 = dup2 (fd[0], STDIN_FILENO);
	close (fd[1]);
	n = read(STDIN_FILENO, line, MAXLINE);
	write (STDOUT_FILENO, line, n);
	printf(" Child Finished \n");
}

int standout = STDOUT_FILENO;
int standin = STDIN_FILENO;
int standerr = STDERR_FILENO;

//printf(" Standard out :%d \n Standard in: %d \n Standard err :%d \n", standout, standin, standerr); 
//printf("fd 0: %d, fd 1: %d", fd[0], fd[1]);

return 0;
}
