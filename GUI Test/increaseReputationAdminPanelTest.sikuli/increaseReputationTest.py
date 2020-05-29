if(not exists("1590765672108-1.png")):
    click("1590765489265-2.png")
wait("1590765672108-1.png")
while(not exists("1590772570205.png")):
    wheel(WHEEL_DOWN, 5)
    sleep(0.1)

search = find("1590772570205.png")
click(search.find("1590772624592.png"))
type("Via Roma Turin Piemont Italy")
sleep(3)
type(Key.DOWN + Key.ENTER)
click(search.find("1590772775981.png"))
wheel(WHEEL_DOWN, 2)
sleep(0.5)
click("1590774001315.png")
sleep(0.5)
while(not exists("1590774223598.png")):
    wheel(WHEEL_UP, 3)
    sleep(0.1)
admin = find("1590774361598.png")
admin.find("1590774371154.png")