import sqlite3


conn = sqlite3.connect("database.db")


cur = conn.cursor();

sql = """
INSERT INTO users(userId, username, password, userGroup) values('THSE1001', 'admin', 'Admin123', 'admin' ),
values('THSE1002', 'abebe', 'abebe123', 'advisor' ),
values('THSE1003', 'abel', 'abel123', 'teacher' ),
values('THSE1004', 'abera', 'abera123', 'staff' ),
values('THSS1001', 'feven', 'feven123', 'student'),
values('THSS1002', 'girma', 'girma123', 'student'),
values('THSS1003', 'abdu', 'abdu123', 'student' ),
values('THSS1004', 'selam', 'selam123', 'student')"""

try:
	cur.execute(sql);
except Exception as e:
	print(e)

data = cur.fetchall();
conn.commit();

for d in data:
	print(d)

conn.close()