#include <stdio.h>
#include <signal.h>

int main()
{
	int x = 0;

	printf("Setting up Alarm\n");
	alarm(5);
	pause();

}
