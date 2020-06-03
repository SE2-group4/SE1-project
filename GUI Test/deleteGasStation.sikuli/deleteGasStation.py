if(not exists("1590765672108-1.png")):
    click("1590765489265-2.png")
wait("1590765672108-1.png")
wheel(WHEEL_DOWN, 10)
sleep(0.5)

while(exists("1590767882344-1.png")):
    click("1590767882344-1.png") 
    sleep(0.2)
if(exists("1590768238555-1.png")):
    raise Exception('Gas station has not been deleted')
    