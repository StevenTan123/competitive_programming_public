#include <bits/stdc++.h>
using namespace std;

const int DIM = 25;

struct Basis {
    // basis[i] stores the vector in the basis with lowest bit i, or -1 if it doesn't exist.
    vector<int> basis;
    Basis() : basis(DIM, -1) {}

    int lowest_bit(int vec) {
        for (int i = 0; i < DIM; i++) {
            if (vec & (1 << i)) {
                return i;
            }
        }
        return DIM;
    }
    
    // Tries to write vec as a linear combination of existing basis vectors. If it can, 
    // it returns 0. Otherwise, it returns the vector that should be added to the basis. 
    int check_vector(int vec) {
        for (int i = 0; i < DIM; i++) {
            if (vec & (1 << i)) {
                if (basis[i] == -1) {
                    return vec;
                } else {
                    vec ^= basis[i];
                }
            }
        }
        return 0;
    }

    void add_vector(int vec) {
        int add = check_vector(vec);
        if (add != 0) {
            basis[lowest_bit(add)] = add;
        }
    }
    
    bool spans(int vec) {
        return check_vector(vec) == 0;
    }
};
