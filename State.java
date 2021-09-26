
/*
 * Define states to be used in solving the missionaries and
 * cannibals problem. 
 */


class State {
        static int N; // There are N missionaries and N Cannibals
	String m; // 
	int c; // cannibals on left bank
	int b; // 0 for boat on left bank, 1 for boat on the right
	State pred; // predecessor state

	State(State s) {
		m = s.m;
		c = s.c;
		b = s.b;
	}

	State(String M, int C, int B) {
		m = M;
		c = C;
		b = B;
	}

        static void setN(int n) 
        {
                N = n;
        }

	boolean legal()
	/* is this a legal state? */
	{
		if (m < 0 || m > N || c < 0 || c > N)
			return false;
		if (m > 0 && c > m) // can't have more cannibals on left...
			return false;
		if (N - m > 0 && N - c > N - m) // ...or on right bank
			return false;
		return true;
	}

	boolean equiv(State s)
	/* Equivalent states? */
	{
                if(s.m == m && s.c == c && s.b == b)
                   return true;
                else
                   return false;
	}

	/**
	 * 
	 * move M missionaries and C cannibals and return the resulting state
	 */
	State move(int M, int C) {
		State newState = new State(this);
		if (b == 0) {
			newState.m = m - M;
			newState.c = c - C;
			newState.b = 1;
		} else {
			newState.m = m + M;
			newState.c = c + C;
			newState.b = 0;
		}
		newState.pred = this;
		return newState;
	}

	void display()
	{
		//System.out.println(m + " " + c + " " + (b == 0 ? "Left" : "Right"));
		System.out.println(m + " " + c + " " + b);
	}
}
