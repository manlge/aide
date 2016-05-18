var bypassDomains = ['i.yonyou.com', 'portal.yonyou.com'];

function FindProxyForURL(url, host) {
	if (isPlainHostName(host) // including localhost
	|| shExpMatch(host, "*.local")) {
		return "DIRECT";
	}
	// only checks plain IP addresses to avoid leaking domain name
	if (/^[0-9.]+$/.test(host)) {
		if (isInNet(host, "10.0.0.0", "255.0.0.0") ||
		isInNet(host, "172.16.0.0",  "255.240.0.0") ||
		isInNet(host, "192.168.0.0",  "255.255.0.0") ||
		isInNet(host, "127.0.0.0", "255.255.255.0")) {
			return "DIRECT";
		}
	}
	// Lantern desktop version proxies only http(s) and ws(s)
	if (url.substring(0, 4) != 'http' && (url.substring(0, 2) != 'ws')) {
		return "DIRECT";
	}
	for (var d in bypassDomains) {
		if (host == bypassDomains[d]) {
			return "DIRECT";
		}
	}
	return "PROXY 172.20.6.179:8787; DIRECT";
}