package fr.app.views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

public class AdminHome extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AdminHome() {
		
		JLabel lblAdminHome = new JLabel("Admin Home :");
		lblAdminHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminHome.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblListeDesJoueurs = new JLabel("Liste des joueurs :");
		lblListeDesJoueurs.setHorizontalAlignment(SwingConstants.CENTER);
		
		table = new JTable();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblListeDesJoueurs, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdminHome, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdminHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblListeDesJoueurs)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
