# 架构,整个报文采用2进制，
为了防止图片乱码后无法重新转回字节数组，报文转String采用的编码是 StandardCharsets.ISO_8859_1
1、（请求）设备端发起TCP连接请求，平台回复一个AT+GETDEVICEID指令获取设备的MAC码作为设备的设备编码（deviceNumber）
> AT+GETDEVICEID\r\n

2、（回复）设备回复AT+GETDEVICEID指令，平台判断设备是否存在，不存在添加设备
> AT+GETDEVICEID\r\nMAC:88888888\r\nOK\r\n

3、查询数据库有否有指令存在，存在就下发对应指令
测试指令
> AT\r\n

读数据指令
> AT+READ\r\n

读数据和图片指令
> \r\nAT+READPIC\r\n

读数据和图片指令(模拟报文)
```json
41 54 2B 72 65 61 64 70 69 63 0D 0A 44 69 67 69 74 73 3A 20 30 2C 30 2C 36 2C 37 2C 39 0D 0A 63 6F 6E 66 69 64 65 6E 63 65 3A 20 31 2E 30 30 2C 31 2E 30 30 2C 31 2E 30 30 2C 30 2E 39 33 2C 31 2E 30 30 0D 0A 54 2E 43 2E 28 6D 73 29 3A 0D 0A 09 09 43 41 4D 09 31 34 39 31 0D 0A 09 09 59 09 35 0D 0A 09 09 53 45 47 09 32 36 32 35 0D 0A 09 09 43 41 4C 43 09 31 31 36 30 0D 0A 44 61 74 61 3A 0D 0A FC FC FC FC F1 50 2A 00 00 00 00 00 00 00 00 07 28 E5 FC FC FC FC FC FC F6 03 00 00 00 16 14 0F 10 12 02 00 00 15 EF FC FC FC FC FC 2D 00 01 29 BF E9 D1 E9 E9 FA 8A 20 04 00 1D FC FC FC FC FC 06 00 21 F1 E3 D9 E1 F1 EB DF E3 FC 35 00 02 F8 FC FC FC F4 08 00 0A DB DD DF EF F1 F1 EF ED FC 7D 00 05 F6 FC FC FC FC 0D 00 00 1F F8 E3 ED FC F6 EF FC FC 27 00 0B FC FC FC FC FC AF 02 00 00 2C 85 A4 AB B2 CF 2F 00 00 00 92 FC FC FC FC FA FC C3 00 00 00 00 00 00 00 00 00 00 00 72 FC FC FC FC FC FC FC FC E1 A2 04 01 02 00 00 00 00 04 85 FC FC FC FC FC FC FC FC FC FC EB E5 E5 D9 E9 C1 B4 F6 FC FC FC FC FC FC FC FC FC FC FC FC FC F1 E7 F8 FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FC FA F6 E9 F6 E1 DF C5 10 01 00 00 00 00 00 2C D7 F8 FC FC FC FC F6 F6 ED E1 CF DF D3 C7 E7 CD D7 C1 A8 DD F1 FC FC FC FC FC FC EF F6 F8 7F 27 2F 23 1A 1D 24 39 93 DF F6 FC FC FC FC FC FC FC BA 1B 00 00 00 00 00 00 00 00 02 1B B0 FC FC FC FC FA FC F4 27 00 00 00 0E 11 0F 11 12 03 00 00 01 8D FC FC FC FC FC 64 00 00 19 8E F8 E9 F4 F1 E9 C3 3E 05 00 13 F8 FC FC F6 EB 21 00 03 E3 CF D1 D7 D5 D1 D3 CD C3 A2 00 00 95 FC F8 EB EF 3B 00 02 E5 E3 DB D9 DB D9 DD DB D7 72 00 00 A9 FC F1 EB EF 52 00 00 03 B8 C1 D1 DB D9 E3 EB ED 4E 00 00 BD F4 ED E9 F4 C9 23 00 00 05 75 AF 92 95 8B 43 02 00 00 3B FC FA F1 F8 FC EF D9 19 02 00 00 00 00 00 00 00 00 00 24 ED FC FC FC FC FC FA F6 F4 83 09 04 04 02 03 03 00 00 3D F4 FC FC FC FC FC FC FC F4 D9 CB DF BF BD B4 C7 BD C3 D3 FA FC FC FC FC FC FC FC FA F8 E9 82 1C 1B 24 23 25 25 51 DF F6 FC FC FC FA ED DD D9 9C 00 00 01 51 C5 DB E3 E7 E1 DF E7 B6 00 00 37 FC FC F1 ED D9 3A 00 00 00 39 90 90 97 95 80 07 00 00 02 BF FA F6 FA F4 FC CD 57 00 00 00 00 00 00 00 00 00 00 02 B0 EF FA F8 FA F8 EF DB CD A4 47 09 00 00 00 00 00 00 05 AB FC FC FC FA FC FC EB E9 DD E3 CF B6 8B 88 95 9A 92 9A D9 F8 FC FC FC FC FC EF ED E9 E9 EB DD CD BB B8 44 2D 2C 43 64 EB F6 FC FC FC FA ED EB D7 21 37 E5 D5 8B 02 00 00 00 00 01 0D 88 FC FC FC F1 F1 C7 0A 00 12 DD 85 03 00 00 0D 16 0A 00 00 10 F4 FC FC F4 E7 88 00 00 A8 E5 15 00 00 32 C5 CD D3 17 00 01 8A FC FC EB E5 B4 00 00 CF E1 16 00 02 C7 D9 C5 CD DB 00 00 54 FC FC FA F4 90 00 00 7F DF 0E 00 02 A9 C7 C3 C9 CB 00 00 47 FC FC F8 EB D7 1F 00 01 7F 95 0A 06 D3 DB DF EB 9A 00 00 4D FC FC FC E9 E1 5F 00 00 02 6D 48 00 0E 9F 97 69 01 00 1A E1 FC FC FA F8 EF E1 E1 12 00 00 00 00 00 00 00 00 00 13 DD FC E9 E9 E9 E5 E7 CB 06 00 00 09 35 3D 39 42 44 0A 00 00 11 98 EB EB E5 E5 D5 9C 00 07 43 D3 DF BA CF C7 D9 A1 69 03 00 24 EF EB E3 DB D5 0B 00 17 D7 D1 DB DF E5 EB F4 F8 F6 0F 00 24 E7 E9 E9 DF F1 07 00 02 56 C3 E7 E5 DF DB E9 DB F8 0D 00 41 ED EF E9 E3 CD 21 00 00 01 49 9A A6 C3 D1 C3 56 0B 00 00 A4 FC F4 F4 DD E9 AD 10 00 00 00 05 05 04 06 07 00 00 00 57 F1 F8 FA EF E3 D3 C7 90 3B 15 00 00 00 00 00 00 00 00 50 F6 FC FA FC ED E5 D9 C1 B0 BA 9D 51 50 52 48 5B 4F 45 7F E5 FC FC F8 FC EF E7 DD DD C3 BA 7F 6C 4D 41 48 3A 45 51 BA F8 FC FC FA EB E5 E5 DD CB 5D 12 00 00 00 00 00 00 00 02 36 7C E3 FC F8 ED E9 E5 E5 D1 00 00 00 00 03 03 06 05 01 00 00 02 56 FC FC ED E7 D7 C7 2E 00 00 0B 82 B4 9A 97 BA 90 17 04 00 03 75 FA ED DD D7 B2 02 00 46 A9 BD BD CF D1 C9 E7 DB D1 06 00 2D F1 E9 DD D7 C7 00 00 44 A8 CD D7 DB DB E3 FC DF E1 05 00 22 D9 DD E1 DD E1 D5 D1 D1 CB C5 C7 D5 DB DB E3 ED EF F4 FA FA D1 DB E1 DF E1 D3 D3 C9 A8 76 9A 6F 70 7F 82 8A CD D7 ED FA D1 D7 DB E1 D3 D3 A9 65 05 01 00 00 00 00 01 04 27 5F D5 EF CF D3 D7 D1 CB C9 24 00 00 00 01 00 00 00 00 00 00 00 2C DF D3 D3 D9 D1 CF B2 00 00 12 73 77 41 02 00 00 02 01 00 09 C7 D3 D7 D7 D5 CB 17 00 04 C1 C9 B6 92 3A 00 00 5F 2B 01 01 3E D9 D3 CF CB E1 0E 00 0A CF B8 B2 C7 44 00 00 AD CF 0A 00 0D D3 D3 D3 C9 CD 2A 00 09 82 B8 C1 E9 13 00 00 95 B2 09 00 0A E3 DD D5 CD C9 90 04 00 0B 4F 6C 3E 00 00 02 BD E9 01 00 21 E5 DF D9 D7 E5 E5 12 01 00 00 00 00 00 00 4A EF 7D 05 1E BD E5 DD DD DD D7 D9 9A 6D 06 05 04 03 06 61 F4 E5 B4 AF EB FC E9 E5 ED EB E5 DF D5 AD 7A 75 75 7F B4 C9 C1 DF E3 F6 FC FC EF F1 EF EF E7 E9 C5 9C 15 0B 09 06 06 07 0B 25 A9 B8 F8 FC ED EB ED EB E7 DB 39 00 00 00 00 00 00 00 00 00 01 0C 92 FC 0D 0A 4F 4B 0D 0A
```