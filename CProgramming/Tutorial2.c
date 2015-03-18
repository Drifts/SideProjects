#include <stdio.h>
#include <unistd.h>

int main()
{
int n;
int fd[2];
int pid;
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
	printf("Parent Finished \n");
	write(fd[1], "Hello World\n", 12);
	
	n = read(fd[0], line, MAXLINE);
	sleep(1);
	write (STDOUT_FILENO, line, n);
}
else
{
	printf("Child Finished \n");
	write(fd[1], "lancaster \n", 10);
	n = read(fd[0], line, MAXLINE);
	sleep(3);
	write (STDOUT_FILENO, line, n);
	
}
return 0;
}
