package net.rootdev.jenajung;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;

import java.awt.Dimension;
import javax.swing.JFrame;

import net.rootdev.jenajung.JenaJungGraph;
import net.rootdev.jenajung.Transformers;
import net.rootdev.jenajung.Transformers.EdgeT;
import net.rootdev.jenajung.Transformers.NodeT;


public class JenaJungJFrame {
	public static void makeJFrame(Model model) {
		Graph<RDFNode, Statement> g = new JenaJungGraph(model);
		Layout<RDFNode, Statement> layout = new FRLayout(g);
		layout.setSize(new Dimension(300, 300));
		
//	    BasicVisualizationServer<RDFNode, Statement> viz =
//				new BasicVisualizationServer<RDFNode, Statement>(layout);
	    
	    VisualizationViewer<RDFNode, Statement> viz =
				new VisualizationViewer<>(layout, new Dimension(400, 400));
	    
	    DefaultModalGraphMouse<EdgeT, NodeT> graphMouse = new DefaultModalGraphMouse<>();
	    graphMouse.setMode(DefaultModalGraphMouse.Mode.PICKING);
	    viz.setGraphMouse(graphMouse);
		
		RenderContext context = viz.getRenderContext();
		context.setEdgeLabelTransformer(Transformers.EDGE); // property label
		context.setVertexLabelTransformer(Transformers.NODE); // node label
		viz.setPreferredSize(new Dimension(350, 350));
		JFrame frame = new JFrame("Jena Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viz);
		frame.pack();
		frame.setVisible(true);
	}
}
