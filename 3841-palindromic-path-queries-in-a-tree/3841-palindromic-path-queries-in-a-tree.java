import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] rootMask = new int[n];
        int[] order = new int[n];
        int[] size = new int[n];
        int[] stack = new int[n];
        int timer = 0;
        int top = 0;

        Arrays.fill(parent, -1);
        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            tin[u] = timer;
            order[timer++] = u;
            size[u] = 1;
            rootMask[u] = (parent[u] == u ? 0 : rootMask[parent[u]])
                    ^ (1 << (s.charAt(u) - 'a'));

            for (int v : graph[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            }
        }

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int i = 0; i < n; i++) {
            tout[i] = tin[i] + size[i] - 1;
        }

        int LOG = 17;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        Fenwick bit = new Fenwick(n);

        for (int i = 0; i < n; i++) {
            int u = order[i];
            int prev = i == 0 ? 0 : rootMask[order[i - 1]];
            bit.update(tin[u], rootMask[u] ^ prev);
        }

        char[] chars = s.toCharArray();
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int delta = (1 << (chars[u] - 'a'))
                            ^ (1 << (c - 'a'));

                    bit.update(tin[u], delta);
                    if (tout[u] + 1 < n) {
                        bit.update(tout[u] + 1, delta);
                    }

                    chars[u] = c;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int l = lca(u, v, depth, up);

                int mask = bit.query(tin[u])
                        ^ bit.query(tin[v])
                        ^ (1 << (chars[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int idx, int val) {
            for (idx++; idx <= n; idx += idx & -idx) {
                tree[idx] ^= val;
            }
        }

        int query(int idx) {
            int res = 0;
            for (idx++; idx > 0; idx -= idx & -idx) {
                res ^= tree[idx];
            }
            return res;
        }
    }
}