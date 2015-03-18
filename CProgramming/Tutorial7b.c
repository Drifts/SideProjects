#include <stdio.h>
#include <setjmp.h>

jmp_buf jb;

main()
{
	int x = 0;
	setjmp(jb);
	
	printf("Enter a value for x : ");
	scanf("%d", &x);
	
	printf("x = %d", x);
	
	longjmp(jb,1);
}
