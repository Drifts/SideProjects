#include <stdio.h>
#include <unistd.h>

int main()
{
int n;
int fd2[5];
int pid;
const int MAXLINE = 4096;
char line[MAXLINE];

if (pipe(fd2) < 0)
	printf("Pipe Error");
if ((pid = fork()) < 0)
{
	printf("Fork Error");
}
else if (pid > 0)
{
	close (fd2[0]);
	write(fd2[1], "Hello World\n", 12);
	printf("Parent Finished \n");
}
else
{
	close (fd2[1]);
	n = read(fd2[0], line, MAXLINE);
	printf ("%s \n", line);
	printf("Child Finished \n");
}

fd2[2] = STDIN_FILENO;
fd2[3] = STDOUT_FILENO;
fd2[4] = STDERR_FILENO;


printf("Stand in: %d \n", fd2[2]); // standard input
printf("Stand out: %d \n", fd2[3]); // standard output
printf("Stand Error:%d \n", fd2[4]); // standard error
printf("file des 0: %d \n", fd2[0]); // file descriptor 0
printf("file des 1: %d \n", fd2[1]); // file descriptor 1

return 0;
}
