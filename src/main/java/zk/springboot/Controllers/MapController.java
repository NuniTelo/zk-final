package zk.springboot.Controllers;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;

import java.util.ArrayList;
import java.util.List;

public class MapController extends SelectorComposer<Component> {
    @Wire
    Gmaps mymap;

    @Wire
    Button fetch;

    @Wire
    Button fetchData;

    List<Gmarker>gmarkerList = new ArrayList<Gmarker>();


    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        mymap.setLat(41.326217);
        mymap.setLng(19.8179095);

        //krijojme nje gmarker per te venosur gmarkerat tone
        List<Double>lat = new ArrayList<>();
        lat.add(41.326217);
        lat.add(41.336217);
        lat.add(41.346217);

        List<Double>lng = new ArrayList<>();
        lng.add(19.7179095);
        lng.add(19.8179095);
        lng.add(19.9179095);

        setData(lat, lng);

    }


    @Listen("onClick=#fetch")
    public void fetchData(){
       mymap.removeChild(gmarkerList.get(gmarkerList.size()-1));
//       mymap.appendChild(gmarkerList.get(1));

    }

    @Listen("onClick=#fetchData")
    public void addData(){
        mymap.appendChild(new Gmarker("llaivokel",42.326217,18.7179095));
        fetchData.setDisabled(true);
    }

    public void setData(List<Double> lat, List<Double> lng) {
        for(int i =0 ;i<3;i++){
            //i ri sa here qe te kemi per te shfaqur te dhena ne harte
            gmarkerList.add(new Gmarker("test",lat.get(i),lng.get(i)));

        }
        for (Gmarker mark: gmarkerList)
            {

                mark.setIconImage("http://www.google.com/mapfiles/markerZ.png");
                mark.setParent(this.mymap);
        }
    }

}
