#!/usr/bin/env python
#-*- coding:utf-8 -*-
import urllib.request
resp=urllib.request.urlopen('https://www.cnblogs.com/ECJTUACM-873284962/')
html=resp.read()
print (html)