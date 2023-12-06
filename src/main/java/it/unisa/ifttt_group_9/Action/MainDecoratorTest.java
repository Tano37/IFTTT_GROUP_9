package it.unisa.ifttt_group_9.Action;

class MainDecoratorTest{

    public static void main(String[] args){
        Action selectedAction = new ActionText("3", null);
        selectedAction = new ActionText("2", selectedAction);
        selectedAction = new ActionText("1", selectedAction);
        selectedAction = new ActionAudio("C:\\Users\\alfon\\OneDrive\\Desktop\\Università Magistrale\\Software Engineering\\IFTTT_GROUP_9\\src\\main\\resources\\it\\unisa\\ifttt_group_9\\testAudio.wav", selectedAction);
        selectedAction = new ActionFileLaunch("C:\\Users\\alfon\\OneDrive\\Desktop\\Università Magistrale\\Software Engineering\\IFTTT_GROUP_9\\src\\main\\resources\\it\\unisa\\ifttt_group_9\\draw.exe", "", selectedAction);

        selectedAction.executeAction();
    }




}
