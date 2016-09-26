var bypassDomains = ['i.yonyou.com', 'portal.yonyou.com'];

function FindProxyForURL(url, host) {
	if (isPlainHostName(host) // including localhost
	|| shExpMatch(host, "*.local")) {
		return "DIRECT";
	}
	// only checks plain IP addresses to avoid leaking domain name
	if (/^[0-9.]+$/.test(host)) {
		if (isInNet(host, "10.0.0.0", "255.0.0.0") ||
		isInNet(host, "172.0.0.0",  "255.0.0.0") ||
		isInNet(host, "192.168.0.0",  "255.255.0.0") ||
		dnsDomainIs(host, ".sina.com.cn") ||
		dnsDomainIs(host, ".baidu.com") ||
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
	return "PROXY 127.0.0.1:8788; DIRECT";
}
