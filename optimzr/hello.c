/*
 * hello.c
 *
 *  Created on: Dec 13, 2014
 *      Author: priyanka
 */
#include <stdio.h>

int main() {
	int a = 3;
	int b = 6;
	int c = a * b;
	if (a == 5) {
		c = a * c;
	}
	printf ("%d",c);
}

