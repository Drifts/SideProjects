#include <stdio.h>
#include <unistd.h>

int main()
{
	int x =0;
	int value = 0;
int n;
int pid;


if ((pid = fork()) < 0)
{
	printf("Fork Error");
}
else if (pid > 0)
{
	value= x+1 ;
	printf ("Parent = %d",value);
}
else
{
	printf ("Child = %d",value);
}
return 0;
}
