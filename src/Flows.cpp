#define _CRT_SECURE_NO_WARNINGS
#define _USE_MATH_DEFINES

#include<iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <math.h>
#include <map>
#include <queue>
#include <stdlib.h>
#include <stdio.h>
#include <map>
#include <unordered_map>
#include <unordered_set>
#include <cmath>
#include <complex>
#define ll long long
#define int long long
#define cd complex<double>

using namespace std;

struct FlowGraph {
    struct Edge {
        ll to;
        ll flow, cost, cap;

        Edge(ll to, ll cap, ll flow, ll cost) : to(to), cap(cap), flow(flow), cost(cost) {}
    };

    struct Pair {
        long flow, cost;

        Pair(ll flow, ll cost) : flow(flow), cost(cost) {}
    };

    vector<Edge> edges;
    vector<vector<ll>> graph;
    vector<bool> used;
    vector<ll> lst;

    vector<ll> p;
    vector<ll> pe;
    vector<ll> dist;
    int s, t, n;

    void init(int k) {
        n = k;
        graph.resize(n);
        used.resize(n);
        lst.resize(n);
        p.resize(n);
        pe.resize(n);
        dist.resize(n);
        s = 0;
        t = n - 1;
    }

    void addEdge(int from, int to, ll cap, ll flow, ll cost) {
        graph[from].push_back(edges.size());
        edges.push_back(Edge(to, cap, flow, cost));
        graph[to].push_back(edges.size());
        edges.push_back(Edge(from, 0, -flow, -cost));
    }

    void addBoundedEdge(int from, int to, ll l, ll r, ll flow, ll cost) {
        graph[from].push_back(edges.size());
        edges.push_back(Edge(to, r - l, flow, cost));
        graph[to].push_back(edges.size());
        edges.push_back(Edge(from, 0, -flow, -cost));

        graph[from].push_back(edges.size());
        edges.push_back(Edge(n - 1, l, flow, cost));
        graph[n - 1].push_back(edges.size());
        edges.push_back(Edge(from, 0, -flow, -cost));

        graph[0].push_back(edges.size());
        edges.push_back(Edge(to, l, flow, cost));
        graph[to].push_back(edges.size());
        edges.push_back(Edge(0, 0, -flow, -cost));
    }

    ll res(int x) {
        return edges[x].cap - edges[x].flow;
    }

    ll dfs(int x, ll f, int k) {
        if (used[x]) { return 0; }
        if (x == t) { return f; }
        used[x] = true;

        for (int e : graph[x]) {
            if (res(e) < k) { continue; }
            ll pushed = dfs(edges[e].to, min(f, res(e)), k);
            if (pushed != 0) {
                edges[e].flow += pushed;
                edges[e ^ 1].flow -= pushed;
                return pushed;
            }
        }
        return 0;
    }

    Pair augment() {
        spfa();
        if (p[t] == -1) return Pair(0, 0);
        ll mf = 1e18;
        int mc = 0;
        int curr = t;
        while (curr != s) {
            int e = pe[curr];
            mf = min(mf, res(pe[curr]));
            mc += edges[e].cost;
            curr = p[curr];
        }
        curr = t;
        while (curr != s) {
            int e = pe[curr];
            edges[e].flow += mf;
            edges[e ^ 1].flow -= mf;
            curr = p[curr];
        }
        return Pair(mf, mc * mf);
    }

    void spfa() {
        fill(p.begin(), p.end(), -1);
        fill(pe.begin(), pe.end(), -1);
        fill(dist.begin(), dist.end(), 1e9);
        for (int i = 0; i < n; i++) {
            p[i] = -1;
            pe[i] = -1;
            dist[i] = 1e9;
        }
        dist[s] = 0;
        queue<ll> qq;
        qq.push(s);
        vector<bool> inq (n);
        inq[s] = true;
        while (!qq.empty()) {
            int k = qq.front();
            qq.pop();
            inq[k] = false;
            for (int e : graph[k]) {
                if (res(e) == 0) continue;
                int to = edges[e].to;
                ll w = edges[e].cost;
                if (dist[to] > dist[k] + w) {
                    dist[to] = dist[k] + w;
                    p[to] = k;
                    pe[to] = e;
                    if (!inq[to]) {
                        qq.push(to);
                        inq[to] = true;
                    }
                }
            }
        }
    }

    bool bfs() {
        for (int i = 0; i < n; i++) {
            dist[i] = 1e9;
        }
        dist[s] = 0;
        queue<ll> qq;
        qq.push(s);
        while (!qq.empty()) {
            int k = qq.front();
            qq.pop();
            for (int e : graph[k]) {
                if (res(e) == 0) continue;
                int to = edges[e].to;
                if (dist[to] > dist[k] + 1) {
                    dist[to] = dist[k] + 1;
                    qq.push(to);
                }
            }
        }

        if (dist[t] != 1e9) {
            return true;
        }
        return false;
    }

    ll dinicDfs(int x, long mx) {
        if (x == t) return mx;
        int sum = 0;
        for (; lst[x] < graph[x].size(); lst[x]++) {
            int e = graph[x][lst[x]];
            int to = edges[e].to;
            ll r = res(e);
            if (r == 0 || dist[to] != dist[x] + 1) continue;
            ll pushed = dinicDfs(to, min(mx - sum, r));
            sum += pushed;
            edges[e].flow += pushed;
            edges[e ^ 1].flow -= pushed;
            if (sum == mx) break;
        }
        return sum;
    }

    ll dinic() {
        ll flow = 0;
        while (true) {
            if (!bfs()) break;
            fill(lst.begin(), lst.end(), 0);
            while (true) {
                long f = dinicDfs(s, 1e9);
                if (f == 0) break;
                flow += f;
            }
        }
        return flow;
    }

    ll maxFlow() {
        ll ans = 0;
        int k = 1 << 30;
        while (k >= 1) {
            while (true) {
                fill(used.begin(), used.end(), false);
                ll f = dfs(s, 1e9, k);
                if (f == 0) {
                    break;
                }
                ans += f;
            }
            k /= 2;
        }
        return ans;
    }

    Pair minCostMaxFlow() {
        ll flow = 0;
        ll cost = 0;
        while (true) {
            Pair pair = augment();
            if (pair.flow == 0) {
                break;
            }
            flow += pair.flow;
            cost += pair.cost;
        }
        return Pair(flow, cost);
    }
};


int solve() {

    int r, c;
    cin >> r >> c;

    FlowGraph g = FlowGraph();
    g.init(2 * r * c + 2);

    vector<vector<bool>> matrix (r, vector<bool> (c));
    ll cnt = 0;
    for (int i = 0; i < r; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < c; j++) {
            matrix[i][j] = s[j] == '.';
            cnt += matrix[i][j] ? 1 : 0;
        }
    }

    for (int i = 1; i <= r * c; i++) {
        g.addEdge(0, i, 1, 0, 0);
    }
    for (int i = r * c + 1; i <= 2 * r * c; i++) {
        g.addEdge(i, 2 * r * c + 1, 1, 0, 0);
    }

    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (matrix[i][j]) {
                if (i + 1 < r && matrix[i + 1][j]) {
                    g.addEdge(i * c + j + 1, r * c + (i + 1) * c + j + 1, 1, 0, 0);
                }
                if (i - 1 >= 0 && matrix[i - 1][j]) {
                    g.addEdge(i * c + j + 1, r * c + (i - 1) * c + j + 1, 1, 0, 0);
                }
                if (j + 1 < c && matrix[i][j + 1]) {
                    g.addEdge(i * c + j + 1, r * c + i * c + (j + 1) + 1, 1, 0, 0);
                }
                if (j - 1 >= 0 && matrix[i][j - 1]) {
                    g.addEdge(i * c + j + 1, r * c + i * c + (j - 1) + 1, 1, 0, 0);
                }
            }
        }
    }

    ll res = g.dinic();
    if (res == cnt) {
        cout << "Yes";
    }
    else {
        cout << "No";
    }

    return 0;
}


signed main() {
#ifdef _DEBUG:
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif
    std::ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int T = 1;
    //    cin >> T;
    while (T--) {
        solve();
    }
}