#define _USE_MATH_DEFINES

#include<iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <map>
#include <stdlib.h>
#include <stdio.h>
#include <map>
#include <unordered_map>
#include <unordered_set>
#include <cmath>
#include <complex>
#define ll long long
#define cd complex<double>

using namespace std;

vector<cd> fft(vector<cd> a, bool invert){
    int n = (int) a.size();
    if(n == 1){
        return a;
    }

    vector<cd> a0 (n/2);
    vector<cd> a1 (n/2);

    for(int i = 0; i < n/2; i++){
        a0[i] = a[2*i];
        a1[i] = a[2*i+1];
    }

    vector<cd> y0 = fft(a0, invert);
    vector<cd> y1 = fft(a1, invert);

    double ang = 2*M_PI/n * (invert ? -1 : 1);
    cd w (1);
    cd wn (cos(ang), sin(ang));
    for (int i = 0; i < n/2; i++) {
        a[i] = y0[i] + w*y1[i];
        a[i+n/2] = y0[i] - w*y1[i];
        if (invert)
            a[i] /= 2, a[i+n/2] /= 2;
        w *= wn;
    }

    return a;
}

vector<ll> multiply(vector<ll> a, vector<ll> b){
    vector<cd> fa (a.begin(), a.end()), fb (b.begin(), b.end());
    int n = 1;
    while (n < max (a.size(), b.size())) n <<= 1;
    n <<= 1;

    fa.resize (n), fb.resize (n);
    fa = fft (fa, false);
    fb = fft (fb, false);

    for (int i = 0; i < n; i++) {
        fa[i] = fa[i]*fb[i];
    }
    fa = fft (fa, true);

    vector<ll> res (n);
    for (int i = 0; i < n; i++) {
        res[i] =  (ll) round(fa[i].real());
    }

    return res;
}

vector<ll> polynomialPow(vector<ll> a, int pow){
    if(pow == 1){
        return a;
    }
    if(pow%2 == 0){
        auto res = polynomialPow(a, pow/2);
        return multiply(res, res);
    }else{
        auto res = polynomialPow(a, pow/2);
        return multiply(a, multiply(res, res));
    }
}

int solve() {

    int n, m;
    cin >> n >> m;
    n++;
    m++;
    vector<ll> a (n);
    vector<ll> b (m);

    for(int i = 0; i < n; i++){
        cin >> a[i];
    }
    for(int i = 0; i < m; i++){
        cin >> b[i];
    }

    vector<ll> res = multiply(a, b);

    for(int i = 0; i < n+m-1; i++){
        cout << res[i] << " ";
    }

    return 0;
}


signed main() {
    std::ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int T = 1;
//    cin >> T;
    while (T--) {
        solve();
    }
}